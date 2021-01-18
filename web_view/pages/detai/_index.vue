<template>
  <div class="detaiProduct" v-if="!$isNullOrEmpty(data)">
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
            {{ data.name }}
          </div>
          <div class="d-flex pt-3 color-text">
            <ins class="">{{ data.voteScore }}</ins>
            <v-rating
              :value="5"
              class="ratinga pr-3"
              background-color="orange lighten-3"
              style="color:#EE4D2D; margin-top: -3px"
              small
            ></v-rating>
            <div class="evaluate pr-3">
              <ins class="black--text pl-3">{{ data.voteNumber }}</ins>
              Đánh giá
            </div>
            <div class="evaluate d-flex">
              <div class="black--text pl-3 pr-1 subtitle-2">
                {{ data.sellNumber }}
              </div>
              <div>Đã bán</div>
            </div>
          </div>
          <div class="display-1 py-6 color-text d-flex">
            <div class="title">₫</div>
            {{ $formatMoney({ amount: data.price }) }}
          </div>
          <v-row>
            <v-col cols="2" class="color-bl">Vẫn Chuyển</v-col>
            <v-col cols="10">
              <v-icon>mdi-truck</v-icon>
              Vận Chuyển Tới : {{ data.address }}
            </v-col>
            <v-col cols="2" class="pb-12 color-bl">Số lượng</v-col>
            <v-col cols="10">
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
                  :maxlength="String(data.quantity).length"
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
              <div
                class="color-bl"
                style="position: absolute;top: 238px;right: 220px;"
              >
                {{ data.quantity }} sản phẩm có sẵn
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
          <!-- <template v-for="(data, index) in data.attributeValueInfos">
            <v-col cols="2" class="pr-0 color-bl" :key="index"
              >{{ data.attributeName }}:</v-col
            >
            <v-col cols="9" :key="index" class="pl-0">
              {{ data.attributeValue }}
            </v-col>
          </template> -->
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
            src="/Tich-xanh.png"
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
      data: null,
      person: 1,
      thongbao: false,
      last: 1,
      maxlength: 4,
      src:
        'https://thicongnhanh24h.com.vn/wp-content/uploads/2020/06/lam-003-1024x755.jpg',
      model: null,
      imgs: []
    }
  },
  mounted() {
    this.getDetail(this.$route.params.index)
  },
  methods: {
    getDetail(id) {
      this.$store.dispatch('products/detailProduct', { id: id }).then(res => {
        console.log(String(res.data.data.quantity).length, 'res')
        this.data = res.data.data
        // this.imgs = res.data.data.otherThumbnails
      })
    },
    plusPerson() {
      if (this.person < this.data.quantity) {
        this.person += 1
      } else {
        this.person = this.data.quantity
      }
    },
    minusPerson() {
      if (this.person !== 1) {
        this.person -= 1
      }
    },
    validateNumber(value) {
      if (value < this.data.quantity) {
        this.person = Number(value)
      } else {
        this.person = this.data.quantity
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
