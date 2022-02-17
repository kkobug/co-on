<template>
<div style="padding:5px" v-if="streamManager">
	<ov-video id="priv-video" :stream-manager="streamManager"/>
	<div style="height:1em">{{ clientData }}</div>
</div>
</template>

<script>
import OvVideo from './OvVideo';

export default {
	name: 'UserVideo',

	components: {
		OvVideo,
	},

	props: {
		streamManager: Object,
	},

	computed: {
		clientData () {
			const { clientData } = this.getConnectionData();
			return clientData;
		},
	},
	methods: {
		getConnectionData () {
			const { connection } = this.streamManager.stream;
			return JSON.parse(connection.data);
		},
	},
};
</script>
<style>
#priv-video{
  width: 90%;
}
</style>
