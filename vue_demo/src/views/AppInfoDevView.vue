<template>
  <AppInfoView :role="'dev'" />
</template>
<script>
import AppInfoView from './AppInfoView.vue';
export default {
  name: 'AppInfoDevView',
  components: { AppInfoView }
}
</script> 