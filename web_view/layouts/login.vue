<template>
  <v-app>
    <v-main>
      <client-only>
        <notifications
          group="login"
          :duration="10000"
          position="top right"
          :max="5"
        />
      </client-only>
      <nuxt />
    </v-main>
  </v-app>
</template>

<script>
export default {
  middleware: 'auth',
  created() {
    // this.$vuetify.theme.dark = false
  }
}
</script>

<style scoped>
body {
  background: white;
}
</style>
