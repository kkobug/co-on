<template>
  <div id="main-container" class="container">
    <!-- 수업리스트 -->
    <div id="join" v-if="!session">
      <div class="common-layout">
        <el-container>
          <el-header>
            <el-row >
              <el-col :span="6"><div class="grid-content bg-purple">과목명</div></el-col>
              <el-col :span="6"><div class="grid-content bg-purple-light">교사</div></el-col>
              <el-col :span="6"><div class="grid-content bg-purple">수업설명</div></el-col>
              <el-col :span="6"><div class="grid-content bg-purple-light">화상회의</div></el-col>
            </el-row>
          </el-header>
          <el-main>
            <el-row v-for="classitem in this.classes" :key="classitem">
              <el-col :span="6"><div class="grid-content bg-purple">{{classitem.studyName}}</div></el-col>
              <el-col :span="6"><div class="grid-content bg-purple-light">{{classitem.teacher.tchrName}}</div></el-col>
              <el-col :span="6"><div class="grid-content bg-purple">{{classitem.studyDesc}}</div></el-col>
              <el-col :span="6"><div class="grid-content bg-purple-light" @click="joinSession(classitem.classId)">이동</div></el-col>
            </el-row>

          </el-main>
          <el-footer>Footer</el-footer>
        </el-container>
      </div>
    </div>

    <!--세션 -->
		<div id="session" v-if="session">
      <div>session</div>
			<div id="session-header">
				<h1 id="session-title">{{ mySessionId }}</h1>
				<input v-if="micOn" class="btn btn-large btn-success" type="button" id="buttonMic" @click="micControl" value="MICOFF">
				<input v-else class="btn btn-large btn-success" type="button" id="buttonMic" @click="micControl" value="MICON">
				<input v-if="camOn" class="btn btn-large btn-primary" type="button" id="buttonCam" @click="camControl" value="CAMOFF">
				<input v-else class="btn btn-large btn-primary" type="button" id="buttonCam" @click="camControl" value="CAMON">
				<input class="btn btn-large btn-danger" type="button" id="buttonLeaveSession" @click="leaveSession" value="Leave session">
			</div>
			<div id="main-video" class="col-md-6">
				<user-video :stream-manager="mainStreamManager"/>
			</div>
			<div id="video-container" class="col-md-6">
        <user-video :stream-manager="publisher" @click="updateMainVideoStreamManager(publisher)"/>
				<user-video v-for="sub in subscribers" :key="sub.stream.connection.connectionId" :stream-manager="sub" @click="updateMainVideoStreamManager(sub)"/>

      </div>
    </div>
  </div>
</template>

<script>
import { onMounted } from 'vue'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'

// openvidu
import axios from 'axios';
import { OpenVidu, StreamManager } from 'openvidu-browser';
import UserVideo from '../../video/UserVideo.vue';

axios.defaults.headers.post['Content-Type'] = 'application/json';

const OPENVIDU_SERVER_URL = "https://" + location.hostname + ":4443";
const OPENVIDU_SERVER_SECRET = "MY_SECRET";

export default {
  name: 'Lesson',

  components: {
		UserVideo,
	},

  emits: [
    'click'
  ],

	data () {
		return {
			OV: undefined,
			session: undefined,
			mainStreamManager: undefined,
			publisher: undefined,
			subscribers: [],
			camOn: true,
			micOn: true,
      ovToken: undefined,

			mySessionId: null,         // 세션 이름 (Unique)
			classTitle: null,        // 필요 없을 수 있음
			classDescription: null,  // 필요 없을 수 있음
			userid: null,        // 교사 이름 또는 학생 이름

      classes:undefined
		}
	},

  setup () {
    const router = useRouter()
    const store = useStore()
    const object = undefined
    function moveVideo(){
      router.push({
        name:"video"
      })
    }

    // 페이지 진입시 불리는 훅
    onMounted (() => {
      store.commit('root/setMenuActiveMenuName', 'history')
    })
    return {object,moveVideo}
  },
  methods: {
    getClasses(){
      this.$store.dispatch('root/requestGetClass',this.userId)
      .then(result =>{
        this.classes=result.data
        console.log(this.classes)
      })
      .catch(function(err){
        alert(err)
      })
    },
		joinSession (classId) {
      this.mySessionId=classId
			// --- Get an OpenVidu object ---
			this.OV = new OpenVidu();

			// --- Init a session ---
			this.session = this.OV.initSession();

			// --- Specify the actions when events take place in the session ---

			// On every new Stream received...
			this.session.on('streamCreated', ({ stream }) => {
				const subscriber = this.session.subscribe(stream);
				this.subscribers.push(subscriber);
			});

			// On every Stream destroyed...
			this.session.on('streamDestroyed', ({ stream }) => {
				const index = this.subscribers.indexOf(stream.streamManager, 0);
				if (index >= 0) {
					this.subscribers.splice(index, 1);
				}
			});

			// On every asynchronous exception...
			this.session.on('exception', ({ exception }) => {
				console.warn(exception);
			});

			// --- Connect to the session with a valid user token ---

			// 'getToken' method is simulating what your server-side should do.
			// 'token' parameter should be retrieved and returned by your own backend
			this.getToken(this.classId).then(token => {
        this.ovToken=token
				this.session.connect(token, { clientData: this.userid })
					.then(() => {

						// --- Get your own camera stream with the desired properties ---

						let publisher = this.OV.initPublisher(undefined, {
							audioSource: undefined, // The source of audio. If undefined default microphone
							videoSource: undefined, // The source of video. If undefined default webcam
							publishAudio: this.micOn,  	// Whether you want to start publishing with your audio unmuted or not
							publishVideo: this.camOn,  	// Whether you want to start publishing with your video enabled or not
							resolution: '640x480',  // The resolution of your video
							frameRate: 30,			// The frame rate of your video
							insertMode: 'APPEND',	// How the video is inserted in the target element 'video-container'
							mirror: false       	// Whether to mirror your local video or not
						});

						this.mainStreamManager = publisher;
						this.publisher = publisher;

						// --- Publish your stream ---

						this.session.publish(this.publisher);
					})
					.catch(error => {
						console.log('There was an error connecting to the session:', error.code, error.message);
					});
			});

			window.addEventListener('beforeunload', this.leaveSession)
		},

		leaveSession () {
			// --- Leave the session by calling 'disconnect' method over the Session object ---
			if (this.session) this.session.disconnect();

			this.session = undefined;
			this.mainStreamManager = undefined;
			this.publisher = undefined;
			this.subscribers = [];
			this.OV = undefined;

			window.removeEventListener('beforeunload', this.leaveSession);
		},

		updateMainVideoStreamManager (stream) {
			if (this.mainStreamManager === stream) return;
			this.mainStreamManager = stream;
		},

		/**
		 * --------------------------
		 * SERVER-SIDE RESPONSIBILITY
		 * --------------------------
		 * These methods retrieve the mandatory user token from OpenVidu Server.
		 * This behavior MUST BE IN YOUR SERVER-SIDE IN PRODUCTION (by using
		 * the API REST, openvidu-java-client or openvidu-node-client):
		 *   1) Initialize a Session in OpenVidu Server	(POST /openvidu/api/sessions)
		 *   2) Create a Connection in OpenVidu Server (POST /openvidu/api/sessions/<SESSION_ID>/connection)
		 *   3) The Connection.token must be consumed in Session.connect() method
		 */

		getToken (mySessionId) {
			return this.createSession(mySessionId).then(sessionId => this.createToken(sessionId));
		},

		// See https://docs.openvidu.io/en/stable/reference-docs/REST-API/#post-openviduapisessions
		createSession (sessionId) {
			return new Promise((resolve, reject) => {
				axios
					.post(`${OPENVIDU_SERVER_URL}/openvidu/api/sessions`, JSON.stringify({
						customSessionId: sessionId,
					}), {
						auth: {
							username: 'OPENVIDUAPP',
							password: OPENVIDU_SERVER_SECRET,
						},
					})
					.then(response => response.data)
					.then(data => resolve(data.id))
					.catch(error => {
            console.log('에러 발생')
            console.log(error)
						if (error.response.status === 409) {
							resolve(sessionId);
						} else {
							console.warn(`No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}`);
							if (window.confirm(`No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}\n\nClick OK to navigate and accept it. If no certificate warning is shown, then check that your OpenVidu Server is up and running at "${OPENVIDU_SERVER_URL}"`)) {
								location.assign(`${OPENVIDU_SERVER_URL}/accept-certificate`);
							}
							reject(error.response);
						}
					});
			});
		},

		// See https://docs.openvidu.io/en/stable/reference-docs/REST-API/#post-openviduapisessionsltsession_idgtconnection
		createToken (sessionId) {
			return new Promise((resolve, reject) => {
				axios
					.post(`${OPENVIDU_SERVER_URL}/openvidu/api/sessions/${sessionId}/connection`, {}, {
						auth: {
							username: 'OPENVIDUAPP',
							password: OPENVIDU_SERVER_SECRET,
						},
					})
					.then(response => response.data)
					.then(data => resolve(data.token))
					.catch(error => reject(error.response));
			});
		},

		micControl () {
			this.micOn = !this.micOn
      this.publisher.publishAudio(this.micOn)
		},

		camControl () {
			this.camOn = !this.camOn
      this.publisher.publishVideo(this.camOn)
		}
	},
  created:function(){
      const localvuex=JSON.parse(localStorage.getItem('vuex'))
      this.userId=localvuex["root"]["userid"]
      this.getClasses()
  }
}
</script>
<style lang="scss">
.common-layout {
  .el-header,
  .el-footer {
    background-color: #b3c0d1;
    color: var(--el-text-color-primary);
    text-align: center;
    line-height: 60px;
  }

  .el-footer {
    line-height: 60px;
  }

  .el-aside {
    background-color: #d3dce6;
    color: var(--el-text-color-primary);
    text-align: center;
    line-height: 200px;
  }

  .el-main {
    background-color: #e9eef3;
    color: var(--el-text-color-primary);
    text-align: center;
    line-height: 160px;
    width: 100%;
  }

  body > .el-container {
    margin-bottom: 40px;
  }

  .el-container:nth-child(5) .el-aside,
  .el-container:nth-child(6) .el-aside {
    line-height: 260px;
  }

  .el-container:nth-child(7) .el-aside {
    line-height: 320px;
  }
  .el-row {
    margin-bottom: 20px;
  }
  .el-row:last-child {
    margin-bottom: 0;
  }
  .el-col {
    border-radius: 4px;
  }
  .bg-purple-dark {
    background: #99a9bf;
  }
  .bg-purple {
    background: #d3dce6;
  }
  .bg-purple-light {
    background: #e5e9f2;
  }
  .grid-content {
    border-radius: 4px;
    min-height: 36px;
  }
  .row-bg {
    padding: 10px 0;
    background-color: #f9fafc;
  }
}
</style>
