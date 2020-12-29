<template>
  <v-app>
    <client-only>
      <notifications
        group="login"
        :duration="10000"
        position="top right"
        :max="5"
      />
    </client-only>
    <nuxt />
  </v-app>
</template>

<script>
export default {
  middleware: 'auth',
  created() {
    // this.$vuetify.theme.dark = false
  },
}
</script>

<style scoped>
body {
  background: white;
}
</style>
