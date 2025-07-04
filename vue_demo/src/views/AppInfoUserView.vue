<template>
  <AppInfoView :role="'user'" />
</template>
<script>
import AppInfoView from './AppInfoView.vue';
export default {
  name: 'AppInfoUserView',
  components: { AppInfoView }
}
</script> 