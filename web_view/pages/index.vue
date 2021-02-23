<template>
  <div>
    <div>
      <v-row>
        <v-col cols="8" class="pr-0">
          <v-carousel
            cycle
            height="240"
            hide-delimiter-background
            show-arrows-on-hover
          >
            <v-carousel-item
              v-for="(item, i) in items"
              :key="i"
              :src="$isNullOrEmpty(item.imageUrl) ? '' : item.imageUrl"
            ></v-carousel-item>
          </v-carousel>
        </v-col>
        <v-col cols="4" class="pl-1">
          <v-img
            :src="$isNullOrEmpty(imgTopBaner) ? '' : imgTopBaner"
            alt=""
            max-height="116"
            max-width="406"
          ></v-img>
          <v-img
            class="mt-2"
            :src="$isNullOrEmpty(imgBottomBaner) ? '' : imgBottomBaner"
            alt=""
            max-height="116"
            max-width="406"
          ></v-img>
        </v-col>
        <v-col cols="12">
          <v-card flat="">
            <v-row>
              <v-col
                cols="1"
                v-for="(item, index) in listIcon"
                :key="index"
                class="text-center ml-4 hoverText"
              >
                <div class="d-block">
                  <img :src="ImgIcon(item.icon)" alt="" style="height: 47px;" />
                  <div style="font-size: 13px;">
                    Khung giờ săn sale
                  </div>
                </div>
              </v-col>
            </v-row>
          </v-card>
        </v-col>
        <v-col cols="12">
          <img
            src="https://cf.shopee.vn/file/b107aa9ffbffb88d16661d05d40a43a1_xxhdpi"
            alt=""
            style="height: 108px;"
          />
        </v-col>
        <v-col cols="12">
          <v-card flat>
            <div class="pa-7">DANH MỤC</div>
            <v-divider></v-divider>
            <v-row>
              <v-col
                cols="1"
                v-for="(item, index) in category"
                :key="index"
                class="text-center mx-2 hoverText"
              >
                <div class="d-block" @click="next(item)">
                  <img
                    :src="ImgIcon(item.avatar)"
                    alt=""
                    style="height: 50px;"
                  />
                  <div style="font-size: 13px;">
                    {{ item.name }}
                  </div>
                </div>
              </v-col>
            </v-row>
          </v-card>
        </v-col>
        <v-col cols="12">
          <v-card>
            <v-row>
              <v-col cols="4" class="text-center">
                <img
                  src="https://cf.shopee.vn/file/79ee5adef2867c05f429a53a63c8658c"
                  alt=""
                  style="height: 106px;"
                />
              </v-col>
              <v-col cols="4" class="text-center">
                <img
                  src="https://cf.shopee.vn/file/d2a3c82ae6c1d6a47909c424ed4384f8"
                  alt=""
                  style="height: 106px;"
                />
              </v-col>
              <v-col cols="4" class="text-center">
                <img
                  src="https://cf.shopee.vn/file/785f9d8bf0782474f65962255fe84e39"
                  alt=""
                  style="height: 100px;"
                />
              </v-col>
            </v-row>
          </v-card>
        </v-col>
        <!-- <v-col cols="12">
          <v-card flat>
            <div class="pa-7 d-flex">
              <div class="pr-3" style="border-right: 1px solid;">
                SHOPEE MALL
              </div>
              <div class="pl-3">
                7 Ngày Miễn Phí Trả Hàng Hàng Chính Hãng 100% Miễn Phí Vận
                Chuyển
              </div>
            </div>
            <v-divider></v-divider>
            <v-row>
              <v-col cols="4" class="ma-3">
                <img
                  src="https://cf.shopee.vn/file/5b80ba15097d8c59aca9da23dbd7adad"
                  alt=""
              /></v-col>
              <v-col cols="7">
                <v-row>
                  <v-col
                    cols="4"
                    class="text-center"
                    v-for="(item, indexs) in 6"
                    :key="indexs"
                  >
                    <img
                      src="https://cf.shopee.vn/file/454e8b8b0eb01723b59182e99c8e4338_xhdpi"
                      alt=""
                    />
                    <div style="margin-top: -45px; font-size: 19px;">
                      Ưu đãi đến 50%
                    </div>
                  </v-col>
                </v-row>
              </v-col>
            </v-row>
          </v-card>
        </v-col> -->
        <v-col cols="12">
          <v-card flat>
            <div class="pa-7 d-flex">
              <div class="pr-3 pb-4">
                TÌM KIẾM HÀNG ĐẦU
                <v-sheet class="mx-auto" elevation="0" max-width="1100">
                  <v-slide-group v-model="model1" mandatory show-arrows>
                    <v-slide-item
                      v-for="(item, index) in listHD"
                      :key="index"
                      v-slot="{ active, toggle }"
                    >
                      <div
                        flat
                        :color="active ? 'primary' : 'grey lighten-1'"
                        class="ma-7"
                        style="height: 200px ; width: 200px"
                        @click="toggle"
                      >
                        <v-row
                          class="fill-height"
                          align="center"
                          justify="center"
                        >
                          <v-img
                            height="300"
                            class="cardProduct"
                            :src="ImgIcon(item.thumbnail)"
                          ></v-img>
                          <div
                            style="position: absolute;
                            bottom: 3px;
                            color: white; background-color: rgba(0,0,0,.4);"
                            class="pa-2"
                          >
                            {{ item.name }}
                          </div>
                        </v-row>
                      </div>
                    </v-slide-item>
                  </v-slide-group>
                </v-sheet>
              </div>
            </div>
          </v-card>
        </v-col>
        <v-col cols="12">
          <v-card flat>
            <div class="pa-7 d-flex">
              <div class="pr-3 pb-4">
                BÁN CHÁY NHẤT
                <v-sheet class="mx-auto" elevation="0" max-width="1100">
                  <v-slide-group v-model="model" mandatory show-arrows>
                    <v-slide-item
                      v-for="(n, index) in listBC"
                      :key="index"
                      v-slot="{ active, toggle }"
                    >
                      <div
                        flat
                        :color="active ? 'primary' : 'grey lighten-1'"
                        class="ma-7"
                        style="height: 200px ; width: 200px"
                        @click="toggle"
                      >
                        <v-row
                          class="fill-height"
                          align="center"
                          justify="center"
                        >
                          <v-img
                            height="300"
                            class="cardProduct"
                            :src="ImgIcon(n.thumbnail)"
                          ></v-img>
                          <div
                            style="position: absolute;
                            bottom: 3px;
                            color: white; background-color: rgba(0,0,0,.4);"
                            class="pa-2"
                          >
                            {{ n.name }}
                          </div>
                        </v-row>
                      </div>
                    </v-slide-item>
                  </v-slide-group>
                </v-sheet>
              </div>
            </div>
          </v-card>
        </v-col>
        <v-col cols="12">
          <v-card flat>
            <div class="pa-7 d-flex">
              <div class="pr-3 pb-4">
                SIÊU GIẢM GIÁ
                <v-sheet class="mx-auto" elevation="0" max-width="1100">
                  <v-slide-group v-model="model" mandatory show-arrows>
                    <v-slide-item
                      v-for="(n, index) in listDG"
                      :key="index"
                      v-slot="{ active, toggle }"
                    >
                      <div
                        flat
                        :color="active ? 'primary' : 'grey lighten-1'"
                        class="ma-7"
                        style="height: 200px ; width: 200px"
                        @click="toggle"
                      >
                        <v-row
                          class="fill-height"
                          align="center"
                          justify="center"
                        >
                          <v-img
                            height="300"
                            class="cardProduct"
                            :src="ImgIcon(n.thumbnail)"
                          ></v-img>
                          <div
                            style="position: absolute;
                            bottom: 3px;
                            color: white; background-color: rgba(0,0,0,.4);"
                            class="pa-2"
                          >
                            {{ n.name }}
                          </div>
                        </v-row>
                      </div>
                    </v-slide-item>
                  </v-slide-group>
                </v-sheet>
              </div>
            </div>
          </v-card>
        </v-col>
        <v-col cols="12">
          <v-card flat>
            <div class="pa-7">GỢI Ý HÔM NAY</div>
            <div class="px-12">
              <productComponents :data="dataCategory"></productComponents>
            </div>
            <v-pagination
              class="pb-3"
              v-model="page"
              :length="pageSizeTotal"
              prev-icon="mdi-menu-left"
              next-icon="mdi-menu-right"
              @input="nextPage(page)"
            ></v-pagination>
          </v-card>
        </v-col>
      </v-row>
    </div>
  </div>
</template>

<script>
import productComponents from '~/components/productComponents/product'
import URL from '../assets/configurations/Base_Url'
export default {
  components: {
    productComponents
  },
  mounted() {
    this.searchTrending()
    this.ListProduct()
    this.ListBaner()
    this.newsFeed()
    this.flashSale()
    this.sellingProduct()
    this.home()
  },
  methods: {
    next(item) {
      this.$router.push(`/detailProduct/${item.id}`)
    },
    ImgIcon(value) {
      return URL.url + '/shopnow/file/downloadFile/' + value
    },
    home() {
      this.$store.dispatch('trangChu/home', {}).then(res => {
        this.category = res.data.data
      })
    },
    searchTrending() {
      this.$store
        .dispatch('trangChu/searchTrending', {
          pageIndex: 1,
          pageSize: 50
        })
        .then(res => {
          this.listHD = res.data.data.content
        })
    },
    sellingProduct() {
      this.$store
        .dispatch('trangChu/sellingProduct', {
          pageIndex: 1,
          pageSize: 50
        })
        .then(res => {
          this.listBC = res.data.data.content
        })
    },
    flashSale() {
      this.$store
        .dispatch('trangChu/flashSale', {
          pageIndex: 1,
          pageSize: 50
        })
        .then(res => {
          this.listDG = res.data.data.content
        })
    },
    ListBaner() {
      this.$store.dispatch('trangChu/ListBaner', {}).then(res => {
        this.items = res.data.data
        this.imgTopBaner = this.items[0].imageUrl
        this.imgBottomBaner = this.items[1].imageUrl
      })
    },
    newsFeed() {
      this.$store.dispatch('trangChu/newsFeed', {}).then(res => {
        this.listIcon = res.data.data
        console.log(this.listIcon)
      })
    },
    ListProduct() {
      this.$store
        .dispatch('trangChu/suggestionToday', {
          pageIndex: this.page,
          pageSize: this.pageSize,
          orderBy: '1,2'
        })
        .then(res => {
          this.dataCategory = res.data.data.content
          this.pageSizeTotal = Math.ceil(res.data.data.total / this.pageSize)
        })
    }
  },
  data() {
    return {
      category: [],
      productCategoryId: 1,
      page: 1,
      pageSize: 10,
      pageSizeTotal: null,
      dataCategory: [],
      imgTopBaner: null,
      imgBottomBaner: null,
      listIcon: [],
      listSp: [],
      model: null,
      model1: null,
      model2: null,
      model3: null,
      listHD: [],
      listBC: [],
      listDG: [],
      items: []
    }
  }
}
</script>

<style lang="scss" scoped>
.hoverText:hover {
  transition: all 0.5s;
  margin-top: -5px;
}
</style>
