<template>
  <v-app>
    <client-only>
      <notifications
        group="main"
        :duration="7000"
        position="top right"
        :max="5"
      />
    </client-only>

    <v-app-bar
      :clipped-left="clipped"
      flat
      fixed
      app
      light
      class="border-bot-mbf "
    >
      <v-container class="py-0" style="width: 1200px;">
        <v-row>
          <v-col cols="6" class="pt-0" style="margin-top: -9px;">
            <a href="">Kênh Người Bán</a>
            | <a href="">Tải ứng dụng</a> | <a href="">Kết nối</a>
            <v-icon color="white" size="17" class="pb-1"> mdi-facebook </v-icon>
            <v-icon color="white" size="17" class="pb-1"> mdi-camera </v-icon>
          </v-col>
          <v-col
            cols="6"
            align="end"
            class="pt-0 d-flex"
            style="margin-top: -9px;"
          >
            <v-spacer></v-spacer>
            <v-icon color="white" size="17" class="pb-1">
              mdi-bell-outline
            </v-icon>
            <a href="" class="pr-4">Thông báo</a>
            <v-icon color="white" size="17" class="pb-1">
              mdi-help-circle-outline
            </v-icon>
            <a href="" class="pr-4">Trợ giúp</a>
            <a v-if="checkLogIn()">Đăng Ký |</a>
            <a v-if="checkLogIn()" class="pl-1" @click="logIn">Đăng nhập</a>
            <span v-else>
              <v-menu v-model="showMenu" absolute offset-y>
                <template v-slot:activator="{ on, attrs }">
                  <v-avatar size="25" class="avatarCusTom">
                    <img
                      src="https://phunugioi.com/wp-content/uploads/2020/03/anh-gai-xinh-cute-sieu-de-thuong.jpg"
                      v-bind="attrs"
                      v-on="on"
                    />
                  </v-avatar>
                  {{ username }}
                </template>
                <v-list dense class="py-0">
                  <v-list-item-group>
                    <nuxt-link class="nuxt-link text-decoration-none" to="/">
                      <v-list-item class="pl-2">
                        <v-list-item-icon class="mr-0 pt-1">
                          <v-icon>mdi-account</v-icon>
                        </v-list-item-icon>
                        <v-list-item-content>
                          <v-list-item-title class="pt-1">
                            Tài khoản của tôi
                          </v-list-item-title>
                        </v-list-item-content>
                      </v-list-item>
                    </nuxt-link>
                    <v-divider />
                    <v-list-item class="pl-2" @click="open_logout = true">
                      <v-list-item-icon class="mr-0">
                        <v-icon color="#FF296B" size="18">mdi-logout</v-icon>
                      </v-list-item-icon>
                      <v-list-item-content>
                        <v-list-item-title class="nuxt-link">
                          Đăng xuất
                        </v-list-item-title>
                      </v-list-item-content>
                    </v-list-item>
                  </v-list-item-group>
                </v-list>
              </v-menu>
            </span>
          </v-col>
          <v-col cols="2" class="py-0">
            <nuxt-link to="/">
              <img src="fashion_trang.png" width="140" />
            </nuxt-link>
          </v-col>
          <v-col cols="8" class="pb-0">
            <v-text-field
              append-icon="mdi-magnify"
              label="Tìm sản phẩm , thương hiệu và tên shop"
              solo
              hide-details
              flat
              dense
            >
            </v-text-field>
            <div class="list-sp">
              <a href="" class="pr-3">Hoodie Name</a>
              <a href="" class="pr-3">Sandal Nữ</a>
              <a href="" class="pr-3">Dép Nam</a>
              <a href="" class="pr-3">Balo Nữ</a>
              <a href="" class="pr-3">Áo Nữ</a>
              <a href="" class="pr-3">Quần Nam</a>
              <a href="" class="pr-3">Tất Nữ</a>
              <a href="">Váy Trắng</a>
            </div>
          </v-col>
          <v-col cols="2">
            <v-icon color="white" size="35" class="pb-1" @click="buyNow">
              mdi-cart-variant
            </v-icon></v-col
          >
        </v-row>
      </v-container>
    </v-app-bar>

    <v-main>
      <v-container style="width: 1200px;">
        <nuxt />
      </v-container>
    </v-main>
    <v-footer dark padless>
      <v-col cols="12" class="login-footer">
        <v-row class="body-2" style=" color: #707070;">
          <v-col cols="2" class="px-0"></v-col>
          <v-col cols="8" class="pl-0 d-flex">
            <div>
              <div class="pr-2 pb-4">CHĂM SÓC KHÁCH HÀNG</div>
              <div class="caption">
                <div class="pb-2"><a href="">Trung Tâm trợ Giúp</a></div>
                <div class="pb-2"><a href="">Shopee Blog</a></div>
                <div class="pb-2"><a href="">Hướng Dẫn Mua Hàng</a></div>
                <div class="pb-2"><a href="">Hướng Dẫn Bán Hàng</a></div>
                <div class="pb-2"><a href="">Thanh Toán</a></div>
                <div class="pb-2"><a href="">Shopee Xu</a></div>
                <div class="pb-2"><a href="">Vận Chuyển</a></div>
                <div class="pb-2"><a href="">Trả Hàng & Hoàn Tiền</a></div>
                <div class="pb-2"><a href="">Chăm Sóc Khách Hàng</a></div>
                <div class="pb-2"><a href=""> Chính Sách Bảo Hành</a></div>
              </div>
            </div>
            <div class="pl-6">
              <div class="pr-2 pb-4">VỀ SHOPEE</div>
              <div class="caption">
                <div class="pb-2">
                  <a href="">Giới Thiệu Về Shopee Việt Nam</a>
                </div>
                <div class="pb-2"><a href="">Tuyển Dụng</a></div>
                <div class="pb-2"><a href="">Điều Khoản Shopee</a></div>
                <div class="pb-2"><a href="">Chính Sách Bảo Mật</a></div>
                <div class="pb-2"><a href="">Chính Hãng</a></div>
                <div class="pb-2"><a href="">Kênh Người Bán</a></div>
                <div class="pb-2"><a href="">Flash Sales</a></div>
                <div class="pb-2">
                  <a href="">Chương Trình Tiếp Thị Liên Kết Shopee</a>
                </div>
                <div class="pb-2"><a href="">Liên Hệ Với Truyền Thông</a></div>
              </div>
            </div>
            <div class="pl-6">
              <div class="pl-3 pb-3">THANH TOÁN</div>
              <div><img src="image_2020_12_31T08_02_06_198Z.png" alt="" /></div>
              <div class="pl-3 pb-3">ĐƠN VỊ VẬN CHUYỂN</div>
              <div><img src="image_2020_12_31T08_02_15_182Z.png" alt="" /></div>
            </div>
            <div class="pl-6">
              <div class=" pb-3">THEO DÕI CHÚNG TÔI TRÊN</div>
              <div class="pb-2">Facebook</div>
              <div class="pb-2">Instagram</div>
              <div class="pb-2">LinkedIn</div>
            </div>
            <div class="pl-6">
              <div class=" pb-3">TẢI ỨNG DỤNG SHOPEE NGAY THÔI</div>
              <div><img src="image_2020_12_31T08_02_28_720Z.png" alt="" /></div>
            </div>
          </v-col>
        </v-row>
      </v-col>
    </v-footer>
    <yes-no-alert
      :open="open_logout"
      alert-msg="Bạn chắc chắn muốn đăng xuất không?"
      @toggle="open_logout = !open_logout"
      @OK="outside"
    />
  </v-app>
</template>

<script>
import '~/assets/table.scss'

import Cookies from 'js-cookie'
import ULT from '~/plugins/ult'
import APIs from '~/assets/configurations/API_Config'

export default {
  middleware: 'auth',

  created() {},
  mounted() {
    if (!this.$isServer) {
      if (!this.$store.state.app.getRoles) {
        this.$store.dispatch('app/GetRole')
      }
      this.username = Cookies.get('username')
    }
  },
  data() {
    return {
      open_logout: false,
      showMenu: false,
      clipped: true,
      drawer: true,
      fixed: false,

      accountMenuItems: [],
      miniVariant: false,
      right: true,
      rightDrawer: false,
      title: 'PNE-MBF',
      username: '',
      // data đổi mật khẩu tuan.pham
      openChangePass: false,
      allowAccess: true,
      showPassword: false,
      showNewPassword: false,
      showReNewPassword: false,
      selectedItem: {
        password: {
          value: '',
          invalid: false,
          invalidMsg: ''
        },
        newPass: {
          value: '',
          invalid: false,
          invalidMsg: ''
        },
        reNewPass: {
          value: '',
          invalid: false,
          invalidMsg: ''
        }
      }
    }
  },
  watch: {},

  computed: {
    topmenu() {
      return this.getMenuByParent(0)
    }
  },
  methods: {
    outside() {
      this.$router.push('/login')
      Object.keys(Cookies.get()).forEach(function(cookieName) {
        const neededAttributes = {
          // Here you pass the same attributes that were used when the cookie was created
          // and are required when removing the cookie
        }
        Cookies.remove(cookieName, neededAttributes)
      })
    },
    buyNow() {
      this.$router.push('/cart')
    },
    logIn() {
      this.$router.push('/login')
    },
    checkLogIn() {
      if (this.$isNullOrEmpty(Cookies.get('token'))) {
        return true
      } else {
        return false
      }
    },
    getMenuByParent(parent_id) {
      if (!this.$isServer) {
        let lstAccessMenu = []
        let pages = this.$store.state.app.roles
        Array.from(pages).forEach(m => {
          if (m.parent_id === parent_id) lstAccessMenu.push(m)
        })
        //console.log(parent_id, pages, lstAccessMenu)
        return lstAccessMenu
        //console.log(parent_id, lstAccessMenu)
      }
    },
    // đổi mật khẩu tuan.pham
    // toggle dialog change pass
    toggleChangePass() {
      this.selectedItem = {
        password: {
          value: '',
          invalid: false,
          invalidMsg: ''
        },
        newPass: {
          value: '',
          invalid: false,
          invalidMsg: ''
        },
        reNewPass: {
          value: '',
          invalid: false,
          invalidMsg: ''
        }
      }
      this.openChangePass = !this.openChangePass
      this.rightDrawer = false
    },
    // hàm validate input
    setInvalid(payload) {
      this.selectedItem[payload.field] = {
        ...this.selectedItem[payload.field],
        invalid: true,
        invalidMsg: payload.message
      }
    },
    // hàm đổi pass
    async changePass() {
      let error = false
      if (this.selectedItem.password.value === '') {
        error = true
        this.setInvalid({
          field: 'password',
          message: 'Không được để trống'
        })
      }
      if (this.selectedItem.newPass.value === '') {
        error = true
        this.setInvalid({
          field: 'newPass',
          message: 'Không được để trống'
        })
      } else if (this.selectedItem.newPass.value.length < 8) {
        error = true
        this.setInvalid({
          field: 'newPass',
          message: 'Không được ít hơn 8 ký tự'
        })
      }
      if (this.selectedItem.reNewPass.value === '') {
        error = true
        this.setInvalid({
          field: 'reNewPass',
          message: 'Không được để trống'
        })
      } else if (
        this.selectedItem.reNewPass.value !== this.selectedItem.newPass.value
      ) {
        error = true
        this.setInvalid({
          field: 'reNewPass',
          message: 'Mật khẩu không khớp'
        })
      }
      if (!error) {
        let data = {
          pass_old: this.selectedItem.password.value,
          pass_new: this.selectedItem.newPass.value
        }
        try {
          let response = await this.$changePassUser(data)
          let errorCode = response.data.error.code
          if (errorCode === APIs.responses.OK.code) {
            this.toggleChangePass()
            this.showSuccess(this.$router.app, 'Thay đổi mật khẩu thành công.')
            return
          } else if (
            APIs.responses.TOKEN_INVALID.code === errorCode ||
            APIs.responses.TOKEN_INVALID.code === errorCode
          ) {
            this.$TokenInvalid(uniqid(), response)
            return
          } else {
            this.$showWarnNotify(
              this.$router.app,
              APIs.responses[errorCode].message
            )
          }
        } catch (errors) {
          this.$showErrorNotify(
            this.$router.app,
            errors,
            'Không thể kết nối đến server'
          )
        }
      }
    },
    //event input change
    change(value, field) {
      this.selectedItem[field] = {
        ...this.selectedItem[field],
        value,
        invalid: false,
        invalidMsg: ''
      }
    }
  }
}
</script>

<style lang="scss">
.v-list-item__icon {
  margin-right: 20px !important;
}

.v-application--is-ltr
  .v-list--dense.v-list--nav
  .custom-group.v-list-group--no-action
  > .v-list-group__items
  > div
  > .v-list-item {
  padding-left: 50px !important;
}

.v-application--is-ltr
  .v-list--dense
  .v-list-group--sub-group
  .v-list-group__header {
  padding-left: 6px;
}
.avatarCusTom {
  margin-top: -1px;
  width: 27px !important;
}
.border-bot-mbf .v-toolbar__content {
  background-color: #fc5a31;
  height: 127px !important;
  color: white;
  a {
    color: white;
    font-size: 13px;
    text-decoration: none;
  }
  span {
    color: white;
    font-size: 13px;
    cursor: pointer;
  }
  .list-sp {
    a {
      font-size: 12px;
    }
  }
}

.v-list-item--active {
  background-color: white;
}
a {
  text-decoration: none;
  color: #707070;
}
// footer.v-footer.v-sheet.theme--dark.v-footer--padless {
//   background-color: #f5f5f5;
// }
</style>
