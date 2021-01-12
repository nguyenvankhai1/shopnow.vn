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
        <v-col cols="7">
          <div class="headline">
            Áo thun nam tay dài mặc thu đông, giữ nhiệt, chất cotton co giãn, áo
            dài tay dáng ôm cao cấp
          </div>
          <div class="d-flex pt-3 color-text">
            <ins class="">4.8</ins>
            <v-rating
              :value="5"
              class="ratinga pr-3"
              background-color="orange lighten-3"
              style="color:#EE4D2D; margin-top: -3px"
              small
            ></v-rating>
            <div class="evaluate pr-3">
              <ins class="black--text pl-3">24</ins>
              Đánh giá
            </div>
            <div class="evaluate d-flex">
              <div class="black--text pl-3 pr-1 subtitle-2">112</div>
              <div>Đã bán</div>
            </div>
          </div>
          <div class="display-1 py-6 color-text d-flex">
            <div class="title">₫</div>
            99.000
          </div>
          <v-row>
            <v-col cols="2">Vẫn Chuyển</v-col>
            <v-col cols="10"></v-col>
            <v-col cols="2">Size</v-col>
            <v-col cols="10" class="pt-3 pl-0">
              <v-btn outlined small color="#767676">
                <div>XL (45-57kg)</div>
              </v-btn></v-col
            >
            <v-col cols="2" class="pb-12">Số lượng</v-col>
            <v-col cols="10"></v-col>
          </v-row>
          <div>
            <v-btn large style="border: 1px solid;" depressed color="#FFE6DE">
              Thêm Vào Giỏ Hàng
            </v-btn>
            <v-btn large class="white--text" depressed color="#F05D40">
              Mua Ngày
            </v-btn>
          </div>
          <v-divider class="mt-4 mr-3"></v-divider>
          <v-row>
            <v-col cols="4">7 ngày miễn phí trả hàng</v-col>
            <v-col cols="4">Hàng chính hãng 100%</v-col>
            <v-col cols="4">Miễn phí vận chuyển</v-col>
          </v-row>
        </v-col>
      </v-row>
    </v-card>
    <div class="mt-4">
      <v-card flat>
        <v-row class="pl-3 pt-3">
          <v-col cols="12">
            <div class="headline">CHI TIẾT SẢN PHẨM</div>
          </v-col>
          <v-col clos="12">
            <div class="d-flex pb-2 ">
              <div class="pr-3 color-bl">Danh mục:</div>
              <div>Shop > Thời Trang Nam > Áo sơ mi > Dài tay</div>
            </div>
            <div class="d-flex py-2">
              <div class="pr-3 color-bl">Danh mục:</div>
              <div>Shop > Thời Trang Nam > Áo sơ mi > Dài tay</div>
            </div>
            <div class="d-flex py-2">
              <div class="pr-3 color-bl">Danh mục:</div>
              <div>Shop > Thời Trang Nam > Áo sơ mi > Dài tay</div>
            </div>
            <div class="d-flex py-2">
              <div class="pr-3 color-bl">Danh mục:</div>
              <div>Shop > Thời Trang Nam > Áo sơ mi > Dài tay</div>
            </div>
          </v-col>
        </v-row>
      </v-card>
    </div>
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
  .evaluate {
    color: #767676;
    border-left: 1px solid #767676;
    margin-top: -2px;
    font-size: 14px;
  }
  .ratinga {
    button {
      padding: 1px !important;
    }
  }
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
  .color-text {
    color: #ee4d2d;
  }
  .color-bl {
    color: rgba(0, 0, 0, 0.4);
  }
  .cardProduct:hover {
    border: 1px solid #ee4d2d !important;
    transition: all 0.5s;
    margin-top: -5px;
  }
}
</style>
