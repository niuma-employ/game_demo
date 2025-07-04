<template>
  <AppInfoView :role="'admin'" />
</template>
<script>
import AppInfoView from './AppInfoView.vue';
export default {
  name: 'AppInfoAdminView',
  components: { AppInfoView }
}
</script> 