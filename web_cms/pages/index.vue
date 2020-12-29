<template>
  <div></div>
</template>

<script>
export default {
  components: {},
  transition(to, from) {
    if (!from) {
      return 'slide-x-transition'
    }
    return 'slide-x-reverse-transition'
  },
  data: () => ({}),
  computed: {
    breakPoint() {
      switch (this.$vuetify.breakpoint.name) {
        case 'xs':
          return 'mobile'
        case 'sm':
          return 'desktop'
        case 'md':
          return 'desktop'
        case 'lg':
          return 'desktop'
        case 'xl':
          return 'desktop'
        default:
          return 'desktop'
      }
    },
  },
  created() {
    this.$store.commit('app/checkBack', false)

    if (this.$route.fullPath === '/') {
      this.$store.commit('app/setTitle', 'Dashboard')
    }
  },
  methods: {
    clicked(value) {
      this.$store.dispatch('alert/set_notifications', {
        icon: 'success',
        timeout: 6000,
        color: 'white',
        text: `Check message notìication ${value}`,
      })
    },
  },
}
</script>
