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
                  @mousemove="HoverImg(n)"
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
            <v-col cols="2" class="color-bl">Vẫn Chuyển</v-col>
            <v-col cols="10">
              <v-icon>mdi-truck</v-icon>
              Vận Chuyển Tới : Huyện Ba Vì ,Hà Nội
            </v-col>
            <v-col cols="2" class="pb-12 color-bl">Số lượng</v-col>
            <v-col cols="10 ">
              <div
                class="room d-flex"
                style="
              border: 1px solid #ececec;
              border-radius: 3px;
              max-width: 30%;
            "
              >
                <v-btn
                  small
                  depressed
                  style="
                height: 40px;
                border-right: 1px solid #ececec;
                background: #fff;
              "
                  @click="minusPerson"
                >
                  <v-icon small>mdi-minus</v-icon>
                </v-btn>
                <v-text-field
                  v-model="person"
                  hide-details
                  solo
                  dense
                  @input="validateNumber($event)"
                ></v-text-field>
                <v-btn
                  small
                  depressed
                  style="
                height: 40px;
                border-left: 1px solid #ececec;
                background: #fff;
              "
                  @click="plusPerson"
                >
                  <v-icon small>mdi-plus</v-icon>
                </v-btn>
              </div>
            </v-col>
          </v-row>
          <div>
            <v-btn
              large
              style="border: 1px solid;"
              depressed
              color="#FFE6DE"
              @click="addCart"
            >
              Thêm Vào Giỏ Hàng
            </v-btn>
            <v-btn
              large
              class="white--text"
              depressed
              color="#F05D40"
              @click="buyNow"
            >
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
          <v-col cols="2" class="pr-0 color-bl">Danh mục:</v-col>
          <v-col cols="9" class="pl-0">
            Shop > Thời Trang Nam > Áo sơ mi > Dài tay
          </v-col>
          <v-col cols="2" class="pr-0 color-bl">Thương hiệu:</v-col>
          <v-col cols="9" class="pl-0">
            No Brand
          </v-col>
          <v-col cols="2" class="pr-0 color-bl">Chất liệu:</v-col>
          <v-col cols="9" class="pl-0">
            Chất liệu khác
          </v-col>
          <v-col cols="2" class="pr-0 color-bl">Xuất Xứ:</v-col>
          <v-col cols="9" class="pl-0">
            Việt Nam
          </v-col>
          <v-col cols="2" class="pr-0 color-bl">Kho hàng:</v-col>
          <v-col cols="9" class="pl-0">
            11985
          </v-col>
        </v-row>
        <v-col cols="12">
          <div class="headline">MÔ TẢ SẢN PHẨM</div>
        </v-col>
      </v-card>
      <div v-if="thongbao">
        <v-card class="thongbao headline">
          <v-img
            width="100"
            style="margin-left: 130px;"
            src="Tich-xanh.png"
          ></v-img>
          Sản phẩm đã được thêm vào giỏ hàng
        </v-card>
      </div>
    </div>
  </div>
</template>

<script>
import Cookies from 'js-cookie'
export default {
  data() {
    return {
      person: 1,
      thongbao: false,
      last: 1,
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
  mounted() {
    this.getDetail(this.$route.params.index)
  },
  methods: {
    getDetail(id) {
      this.$store.dispatch('products/detailProduct', {
        id: id
      })
    },
    plusPerson() {
      this.person += 1
    },
    minusPerson() {
      if (this.person !== 1) {
        this.person -= 1
      }
    },
    buyNow() {
      if (!this.$isNullOrEmpty(Cookies.get('token'))) {
        this.$router.push('/cart')
      } else {
        this.$router.push('/login')
      }
    },
    addCart() {
      this.thongbao = true
      setTimeout(() => {
        this.thongbao = false
      }, 2000)
    },
    HoverImg(value) {
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
  .thongbao {
    position: fixed;
    top: 345px;
    height: 170px;
    width: 354px;
    left: 708px;
    z-index: 10000;
    opacity: 0.7;
    background-color: black;
    color: aqua;
    text-align: center;
  }
  .v-input__slot {
    box-shadow: 0 0 black !important;
    height: 40px;
    border-radius: 0 0 0 0;
  }
  .v-text-field__slot {
    input {
      text-align: center;
    }
  }
}
</style>
