<template>
  <div
    class="notification pa-2"
    @mousemove="clear_timeout"
    @mouseleave="set_timeout"
  >
    <v-slide-y-transition group>
      <div
        v-for="(notification, idx) in $store.state.alert.notifications"
        :key="`notification-${idx}`"
        class="notification-item"
        @click="dismiss_alert(notification)"
      >
        <v-alert
          height="46"
          elevation="4"
          :color="notification.color"
          class="black--text pa-1 px-4 align-center d-flex"
        >
          <div class="subtitle-1 d-flex mt-2">
            <div v-if="notification.icon === 'success'" class="mr-2">
              <svg
                id="Group_160"
                data-name="Group 160"
                xmlns="http://www.w3.org/2000/svg"
                width="28"
                height="28"
                viewBox="0 0 28 28"
              >
                <circle
                  id="Ellipse_27"
                  data-name="Ellipse 27"
                  cx="14"
                  cy="14"
                  r="14"
                  fill="#1453bf"
                />
                <path
                  id="Path_67"
                  data-name="Path 67"
                  d="M4634.055,7186.175l3.33,3.329,6.183-6.183"
                  transform="translate(-4624.605 -7172.471)"
                  fill="none"
                  stroke="#fff"
                  stroke-linecap="round"
                  stroke-linejoin="round"
                  stroke-width="2"
                />
              </svg>
            </div>
            <div v-if="notification.icon === 'error'" class="mr-2">
              <svg
                id="Group_606"
                data-name="Group 606"
                xmlns="http://www.w3.org/2000/svg"
                xmlns:xlink="http://www.w3.org/1999/xlink"
                width="28"
                height="28"
                viewBox="0 0 28 28"
              >
                <defs>
                  <linearGradient
                    id="linear-gradient"
                    x1="0.5"
                    x2="0.5"
                    y2="1"
                    gradientUnits="objectBoundingBox"
                  >
                    <stop offset="0" stop-color="#ffbc37" />
                    <stop offset="1" stop-color="#ffa41b" />
                  </linearGradient>
                </defs>
                <circle
                  id="Ellipse_27"
                  data-name="Ellipse 27"
                  cx="14"
                  cy="14"
                  r="14"
                  fill="url(#linear-gradient)"
                />
                <g
                  id="Group_166"
                  data-name="Group 166"
                  transform="translate(7.036 7.971)"
                >
                  <g
                    id="Polygon_11"
                    data-name="Polygon 11"
                    fill="none"
                    stroke-linejoin="round"
                  >
                    <path
                      d="M6.127,1.28a1,1,0,0,1,1.674,0L12.917,9.1a1,1,0,0,1-.837,1.547H1.849A1,1,0,0,1,1.012,9.1Z"
                      stroke="none"
                    />
                    <path
                      d="M 6.964353084564209 0.8273220062255859 C 6.64454460144043 0.8273220062255859 6.324737071990967 0.9782371520996094 6.127387046813965 1.280067443847656 L 1.011667251586914 9.104116439819336 C 0.576786994934082 9.769227027893066 1.053977012634277 10.6513671875 1.848636627197266 10.6513671875 L 12.08007717132568 10.6513671875 C 12.87473678588867 10.6513671875 13.35192680358887 9.769227027893066 12.91704654693604 9.104116439819336 L 7.801326751708984 1.280067443847656 C 7.6039719581604 0.9782371520996094 7.284162044525146 0.8273220062255859 6.964353084564209 0.8273220062255859 M 6.964356899261475 -1.172682762145996 C 7.980486869812012 -1.172682762145996 8.919137001037598 -0.664942741394043 9.475236892700195 0.1855268478393555 L 14.59098720550537 8.009616851806641 C 15.19452667236328 8.932686805725098 15.24348640441895 10.10869693756104 14.71875667572021 11.07873725891113 C 14.19402694702148 12.04876708984375 13.18294715881348 12.6513671875 12.08007717132568 12.6513671875 L 1.848636627197266 12.6513671875 C 0.7457666397094727 12.6513671875 -0.2653131484985352 12.04876708984375 -0.7900428771972656 11.07873725891113 C -1.314773559570313 10.10869693756104 -1.265812873840332 8.932686805725098 -0.6622734069824219 8.009616851806641 L 4.453447341918945 0.1855669021606445 C 5.009536743164063 -0.6649227142333984 5.948196887969971 -1.172682762145996 6.964356899261475 -1.172682762145996 Z"
                      stroke="none"
                      fill="#fff"
                    />
                  </g>
                  <g
                    id="Group_165"
                    data-name="Group 165"
                    transform="translate(6.964 4.415)"
                  >
                    <path
                      id="Path_70"
                      data-name="Path 70"
                      d="M15.687,15.25v2.088"
                      transform="translate(-15.687 -15.25)"
                      fill="none"
                      stroke="#fff"
                      stroke-linecap="round"
                      stroke-width="2"
                    />
                    <path
                      id="Path_71"
                      data-name="Path 71"
                      d="M15.687,15.265V15.25"
                      transform="translate(-15.687 -10.995)"
                      fill="none"
                      stroke="#fff"
                      stroke-linecap="round"
                      stroke-width="2"
                    />
                  </g>
                </g>
              </svg>
            </div>
            {{ notification.text }}
          </div>
        </v-alert>
      </div>
    </v-slide-y-transition>
  </div>
</template>
<script>
export default {
  methods: {
    dismiss_alert(notification) {
      this.$store.commit('alert/remove_notification', notification)
    },
    clear_timeout() {},
    set_timeout() {},
  },
}
</script>
<style lang="scss">
.notification {
  position: absolute;
  bottom: 0;
  left: 0;
  z-index: 1000;
  overflow-y: hidden !important;
  overflow-x: visible !important;
  max-height: 100% !important;
  .notification-item {
    cursor: pointer;
  }
}
</style>
