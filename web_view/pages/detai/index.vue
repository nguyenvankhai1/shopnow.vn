<template>
  <div class="detaiProduct">
    <v-card flat>
      <v-row class="pl-3">
        <v-col cols="5">
          <v-img height="400" :src="src"></v-img>
          <v-sheet class="mx-auto" elevation="0">
            <v-slide-group v-model="model" mandatory show-arrows>
              <v-slide-item
                v-for="n in imgs"
                :key="n"
                v-slot="{ active, toggle }"
              >
                <div
                  flat
                  :color="active ? 'primary' : 'grey lighten-1'"
                  class="ma-4 "
                  style="height: 83px ; width: 80px"
                  @click="toggle"
                  @mousemove="demo(n)"
                >
                  <v-row class="fill-height" align="center" justify="center">
                    <v-img height="100" class="cardProduct" :src="n"></v-img>
                  </v-row>
                </div>
              </v-slide-item>
            </v-slide-group>
          </v-sheet>
        </v-col>
      </v-row>
    </v-card>
  </div>
</template>

<script>
export default {
  data() {
    return {
      src:
        'https://thicongnhanh24h.com.vn/wp-content/uploads/2020/06/lam-003-1024x755.jpg',
      model: null,
      imgs: [
        'https://thehekhoinghiep.com/wp-content/uploads/2018/07/Cach-chup-anh-quan-ao-ban-hang-online-dep-12.jpg',
        'https://lh3.googleusercontent.com/proxy/iLfveHFg9lcUKIN-ZCrU4NJJdZpy0jEbITKblLOxQicKTqt4gNNIs6F7nva11XMLjIm80pQDFbushTmPhE7105koq9eNYKR9QpW4lixp3G1ZFjA99Q3If433U8OsY-in15_IgvoiKQ',
        'https://cf.shopee.vn/file/6090ee3c0ac0f296530478f8277e9685',
        'https://chupanh.vn/wp-content/uploads/2018/01/chup-anh-quan-ao-3-1.jpg',
        'https://vn-test-11.slatic.net/p/c50313205bc62651242efe79f2095224.jpg'
      ]
    }
  },
  methods: {
    demo(value) {
      if (this.src !== value) {
        this.src = value
      }
    }
  }
}
</script>

<style lang="scss">
.detaiProduct {
  .v-slide-group__prev.v-slide-group__prev--disabled {
    position: absolute;
    z-index: 1;
    top: 48px;
    left: -13px;
  }
  .v-slide-group__next,
  .v-slide-group__prev {
    min-width: 0px !important;
    max-width: 20px !important;
  }
  .cardProduct {
    border: 1px solid white !important;
  }
  .cardProduct:hover {
    border: 1px solid #ee4d2d !important;
    transition: all 0.5s;
    margin-top: -5px;
  }
}
</style>
