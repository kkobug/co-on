<template>
  <div id="main-container" class="container" style="margin: auto; background: none;">
    <!-- 수업리스트 -->
    <div id="join" v-if="!session">
      <div class="common-layout" style="margin-top: 3vh; padding:10px; " v-if="!session">
          <el-row :gutter="20" style="line-height: 60px; height: 5vh; font-size: 20px; font-weight: bold">
            <el-col :span="3"><div class="grid-content ">과목명</div></el-col>
            <el-col :span="3"><div class="grid-content ">교사</div></el-col>
            <el-col :span="2"><div class="grid-content ">마일리지</div></el-col>
            <el-col :span="8"><div class="grid-content ">수업설명</div></el-col>
            <el-col :span="4"><div class="grid-content ">화상회의</div></el-col>
          </el-row>
          <el-scrollbar height="80%;" >
            <el-main style="line-height: 70px">
              <el-row :gutter="20" v-for="classitem in this.classes" :key="classitem" style="background-color: #ecf0f1; border-radius: 20px">
                <el-col :span="3"><div class="grid-content ">{{classitem[0].studyroom.studyName}}</div></el-col>
                <el-col :span="3"><div class="grid-content ">{{classitem[1]}}</div></el-col>
                <el-col :span="2"><div class="grid-content ">{{classitem[0].stPoint}}</div></el-col>
                <el-col :span="8"><div class="grid-content ">{{classitem[0].studyroom.studyDesc}}</div></el-col>
                <el-col v-if="classitem.length>2" :span="4">
                  <div v-if="this.compareDate(classitem[2].confStart, classitem[2].confEnd) == 2" class="grid-content " >{{classitem[2].confStart.substr(0, 16)}} 부터</div>
                  <div v-else-if="this.compareDate(classitem[2].confStart, classitem[2].confEnd) == 3" class="grid-content ">수업이 없습니다.</div>
                  <div v-else class="grid-content ">{{classitem[2].confEnd.substr(0, 16)}}까지</div>
                </el-col>
                <el-col v-else :span="4">
                  <div>수업이 없습니다.</div>
                </el-col>
                <el-col v-if="classitem.length>2" :span="4">
                  <div v-if="this.compareDate(classitem[2].confStart,classitem[2].confEnd) == 2" class="grid-content con-btn" @click="joinSession(classitem[2])">
                    <el-button style="background-color:red; color:white; border-radius:10px;">ON-AIR</el-button>
                  </div>
                  <div v-else-if="this.compareDate(classitem[2].confStart, classitem[2].confEnd) == 1" class="grid-content con-btn">
                    <el-button style="background-color:grey; color:white; border-radius:10px;">WAIT</el-button>
                  </div>
                  <div v-else-if="this.compareDate(classitem[2].confStart, classitem[2].confEnd) == 3" class="grid-content con-btn">
                  </div>
                </el-col>
                <el-col v-else :span="4"></el-col>
              </el-row>
            </el-main>
          </el-scrollbar>
      </div>
    </div>

    <!--세션 -->
		<div id="session" v-if="session">
      <!-- video -->
      <el-row>
        <el-col :span="24-this.chatOn">
          <div id="session-header">
            <h1 id="session-title">{{ this.nowClass[5] }}</h1>
            <el-button circle v-if="micOn" id="button" @click="micControl" value="MICOFF" class="fa_button">
              <font-awesome-icon icon="microphone-slash" class="fa_icon" />
            </el-button>
            <el-button circle v-else id="buttonMic" @click="micControl" value="MICON" class="fa_button">
              <font-awesome-icon icon="microphone" class="fa_icon"/>
            </el-button>
            <el-button circle v-if="camOn" id="buttonCam" @click="camControl" value="CAMOFF" class="fa_button">
              <font-awesome-icon icon="video-slash" class="fa_icon"/>
            </el-button>
            <el-button circle v-else id="buttonCam" @click="camControl" value="CAMON" class="fa_button">
              <font-awesome-icon icon="video" class="fa_icon"/>
            </el-button>
            <el-button circle v-if="shareOn" id="buttonQuitShare" @click="screenShare" value="Quit share" class="fa_button">
              <font-awesome-icon icon="share-square" class="fa_icon" style="color:red"/>
            </el-button>
            <el-button circle v-else id="buttonScreenShare" @click="screenShare" value="Screen share" class="fa_button">
              <font-awesome-icon icon="share-square" class="fa_icon"/>
            </el-button>
            <el-button circle v-if="chatOn" id="buttonChat" @click="chatControl" value="CHATOFF" class="fa_button">
              <font-awesome-icon icon="comment-slash" class="fa_icon"/>
            </el-button>
            <el-button circle v-else id="buttonChat" @click="chatControl" value="CHATON" class="fa_button">
              <font-awesome-icon icon="comment-dots" class="fa_icon"/>
            </el-button>
            <el-button circle id="buttonLeaveSession" @click="leaveSession" value="Leave session" class="fa_button">
              <font-awesome-icon icon="door-open" class="fa_icon"/>
            </el-button>
            <el-button circle v-if="this.mainStreamManager" @click="viewAll" value="View all" class="fa_button">
              <font-awesome-icon icon="users" class="fa_icon"/>
            </el-button>
          </div>
          <div id="sub-container" class="center_setting">
            <user-video id="sub-video" :stream-manager="publisher" @click="updateMainVideoStreamManager(publisher)"/>
            <user-video id="sub-video" v-for="sub in subscribers" :key="sub.stream.connection.connectionId" :stream-manager="sub" @click="updateMainVideoStreamManager(sub)"/>
          </div>
          <div class="center_setting">
            <div id="main-container">
              <user-video id="main-video" :stream-manager="mainStreamManager"/>
            </div>
          </div>
        </el-col>
        <!-- video -->
        <!-- chat -->
        <el-col :span="this.chatOn">
          <div id="chatContainer">
            <div id="chatComponent">
              <div id="chatToolbar">
                <span> CHAT</span>
              </div>
              <div class="message-wrap" >
                <div v-for="data in this.messageList" :key="data" class="message">
                  <div class="msg-detail">
                    <p>{{data.creationTime.toString().substr(16,8)}}</p>
                    <span class="msg-info">
                      {{ data.nickname }}:
                    </span>
                    <span class="msg-content">
                      {{data.message}}
                    </span>
                  </div>
                </div>
              </div>
              <div id="messageInput">
                <input
                  placeholder="Send a message"
                  autocomplete="off"
                  v-model="this.textInput"
                  v-on:keyup.enter="sendMessage"
                />
                <button v-if="this.textInput" id="sendbutton" @click="sendMessage" style="border:none">
                  <font-awesome-icon icon="arrow-circle-up" style="color:#409EFF; font-size:25px; ms:3px"/>
                </button>
                <button v-else id="sendbutton" style="border:none">
                  <font-awesome-icon icon="arrow-circle-up" style="font-size:25px"/>
                </button>
              </div>
            </div>
          </div>
        </el-col>

        <!-- chat -->
      </el-row>
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
      tokenId:undefined,
			mainStreamManager: undefined,
			publisher: undefined,
			subscribers: [],
      messageList: [],
			camOn: false,
			micOn: false,
      shareOn: false,
      chatOn: 0,

			mySessionId: null,         // 세션 이름 (Unique)
			classTitle: null,        // 필요 없을 수 있음
			classDescription: null,  // 필요 없을 수 있음
			userId: null,        // 교사 이름 또는 학생 이름
      classes:undefined,
      nowClass:undefined,
      today:new Date(),
      textInput:'',
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
    unLoadEvent: function (event) {
      if (this.canLeaveSite) return;

      event.preventDefault();
      event.returnValue = '';
    },
    compareDate(Date1,Date2){
      if (new Date(Date1) > new Date()){
        return 1
      }else if(new Date() > new Date(Date2)){
        return 3
      }else{
        return 2
      }
    },
    getClasses(){
      this.$store.dispatch('root/requeststLesson2', this.userId)
      .then(result =>{
        console.log("1111111111111", result.data)
        this.classes=result.data
      })
      .catch(function(err){
        alert(err)
      })
    },


    sendMessage(){
      var message = this.textInput
      var OV = new OpenVidu();
      var session = OV.initSession();

      this.createToken(this.tokenId).then((token) => {
        session.connect(token, this.userId)
          .then(()=> {
            session.signal({
            data: message,  // Any string (optional)
            to: [],                     // Array of Connection objects (optional. Broadcast to everyone if empty)
            type: 'my-chat'             // The type of message (optional)
          }).then().catch(error => {
              console.error(error);
          })
        })
        .catch(error =>{
          console.error(error);
        });
      })
      this.textInput=''
    },

    screenShare(){
      var OV = new OpenVidu();
      var sessionScreen = OV.initSession();
      this.createToken(this.tokenId).then((token) => {
        sessionScreen.connect(token, { clientData: this.userId+'screen' })
        .then(() => {
            var publisher = OV.initPublisher("html-element-id", { videoSource: "screen" });
            publisher.once('accessAllowed', (event) => {
                publisher.stream.getMediaStream().getVideoTracks()[0].addEventListener('ended', () => {
                  sessionScreen.unpublish(publisher);
                  this.shareOn=false;
                  console.log('User pressed the "Stop sharing" button');
                });
                sessionScreen.publish(publisher);
            });
            publisher.once('accessDenied', (event) => {
                console.warn('ScreenShare: Access Denied');
            });
        })
        .catch((error => {
            console.warn('There was an error connecting to the session:', error.code, error.message);
        }));
      });
      this.shareOn=true;
    },

		joinSession (classitem) {
      this.nowClass=classitem
      this.mySessionId=classitem.confId+classitem.tchrId
      // 수업 입실 axios
      this.$store.dispatch('root/requestConfEnter',{stId:this.userId,confId:classitem.confId})
      .catch(function(err){
        alert(err)
      })

			// --- Get an OpenVidu object ---
			this.OV = new OpenVidu();

			// --- Init a session ---
			this.session = this.OV.initSession();

      this.session.on("signal", (event) => {
        this.messageList.push({
          nickname : event.from.data,
          message : event.data,
          creationTime : new Date(event.from.creationTime)
        })
      });

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
			this.getToken(this.mySessionId).then(token => {
				this.session.connect(token, { clientData: this.userId })
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
      this.$store.dispatch('root/requestConfExit',{stId:this.userId,confId:this.nowClass.confId})
      .then(result =>{
        console.log('퇴실 완료')
      })
      .catch(function(err){
        alert(err)
      })
			window.removeEventListener('beforeunload', this.leaveSession);
		},
		updateMainVideoStreamManager (stream) {
			if (this.mainStreamManager === stream) return;
			this.mainStreamManager = stream;
		},

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
      this.tokenId=sessionId
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
		},

    chatControl(){
      if (this.chatOn==0){
        this.chatOn = 6
      }else {
        this.chatOn = 0
      }
    },
	},
  created:function(){
    const localvuex=JSON.parse(localStorage.getItem('vuex'))
    this.userId=localvuex["root"]["userid"]
    this.getClasses()
  },
  mounted() {
    window.addEventListener('beforeunload', this.unLoadEvent);
  },
  beforeUnmount() {
    window.removeEventListener('beforeunload', this.unLoadEvent);
  },
}
</script>
<style lang="scss">
.fa_button{
  height: 100%;
}
.fa_icon{
  font-size:30px
}
.center_setting{
  display: flex;
  flex-wrap: wrap;
  // flex-direction: column;
  align-content: center;
  justify-content: center;
}
#set_container{
  width:100vw;
  height:100vh;
}
#session-header {
  // height: auto;
}
#sub-container {
  // width: 20% !important;
  // width: 15% ;
  // height: auto;
}
.con-btn{
  cursor:pointer;
}
#main-container {
  width: 80%;
  // width: calc(36%*1920/1080) ;
  // height: 36%;
}
#sub-video {
  width: 15% !important;
  // height: auto;
}
#main-video{
  // height: 100%;
  // width: 50% ;
}
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
    margin-bottom: 10px;
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
// 메세지
#chatContainer {
  position: fixed;
  z-index: 10;
  width: 20%;
  height: 100%;
}
input {
  font-family: 'Ubuntu', sans-serif;
}

#chatToolbar {
  height: 30px;
  background-color: #3d3d3d;
  box-sizing: border-box;
  font-weight: bold;
  font-size: 14px;
  text-align: center;
  padding-top: 4px;
  border-top-left-radius: 6px;
  border-top-right-radius: 6px;
  color: #ffffff;
}

#closeButton {
  // position: absolute;
  right: 0;
  // top: -5px;
}

#chatComponent {
  background-color: black;
  position: absolute;
  z-index: 99999;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  margin: auto;
  height: calc(100% - 30px);
  // width: calc(100% + 30px);
  border-radius: 20px;
}

.message-wrap {
  background-color: #E0E4D7;
  padding: 0 4px;
  height: calc(100% - 80px);
  overflow: auto;
}

.message {
  text-align: start;
  position: relative;
  padding: 7px 0;
}
.user-img {
  position: absolute;
  border-radius: 45px;
  width: 60px;
  height: 60px;
  top: 15px;
}

.msg-detail {
  display: inline-block;
  // background-color: #7ae2a9;
}

.msg-detail p {
  margin: 0;
  font-size: 15px;
}

.msg-info p {
  font-size: 0.8em;
  color: #000000;
  font-style: italic;
}

.msg-content {
  position: relative;
  margin-top: 5px;
  border-radius: 5px;
  padding: 8px;
  color: #000000;
  width: auto;
  max-width: 95%;
}

.text {
  word-break: break-all;
}

/* Start message from other user */

.message.left .msg-detail .msg-info {
  text-align: left;
}

.message.left .msg-detail {
  padding-left: 65px;
}

.message.left .user-img {
  left: -5px;
  border: 1px solid #f0f0f094;
}

.message.left .msg-detail .msg-content {
  background-color: #f0f0f0;
  float: left;
}

/* End message from other user */

/* Start my messages */

.message.right .msg-detail .msg-info {
  text-align: right;
}
.message.right .user-img {
  right: -5px;
  border: 1px solid #c8ffe8ab;
}

.message.right .msg-detail .msg-content {
  background-color: #c8ffe8;
  float: right;
}

/* End my messages */

#messageInput {
  position: absolute;
  bottom: 0px;
  width: 100%;
  background-color: #ffffff;
  text-align: center;
  padding: 10px 0px;
  height: 30px;
  border-bottom-left-radius: 6px;
  border-bottom-right-radius: 6px;
}
#messageInput input {
  width: 80%;
  height: 100%;
  border: none;
  // outline: none;
  font-size: 14px;
  margin-left: -6%;
}
#messageInput button {
  width: auto;
}
#sendButton {
  position: absolute;
  top: 0;
  bottom: 0;
  margin: 20px;
  box-shadow: none !important;
  border:none;

  // right: 10px;
  // margin: auto;
}
// #sendButton mat-icon {
//   margin-left: 3px !important;
//   margin-bottom: 2px !important;
// }

::-webkit-scrollbar {
  width: 8px;
}

::-webkit-scrollbar-thumb {
  background-color: #6b6b6b;
}

.chatComponentLight ::-webkit-scrollbar-thumb {
  background-color: #eeeeee !important;
}

#sub {
  width: 20%;
  height: auto;
}
</style>
