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

    <v-app-bar :clipped-left="clipped" fixed app light class="border-bot-mbf ">
      <v-container class="py-0">
        <v-row>
          <v-col cols="6" class="pt-0" style="margin-top: -9px;">
            <a href="">Kênh Người Bán</a>
            | <a href="">Tải ứng dụng</a> | <a href="">Kết nối</a>
            <v-icon color="white" size="17" class="pb-1"> mdi-facebook </v-icon>
            <v-icon color="white" size="17" class="pb-1"> mdi-camera </v-icon>
          </v-col>
          <v-col cols="6" align="end" class="pt-0" style="margin-top: -9px;">
            <v-icon color="white" size="17" class="pb-1">
              mdi-bell-outline
            </v-icon>
            <a href="" class="pr-4">Thông báo</a>
            <v-icon color="white" size="17" class="pb-1">
              mdi-help-circle-outline
            </v-icon>
            <a href="" class="pr-4">Trợ giúp</a>
            <a href="">Đăng Ký</a> |
            <a href="">Đăng nhập</a>
          </v-col>
          <v-col cols="2" class="py-0">
            <img src="fashion_trang.png" width="140" />
          </v-col>
          <v-col cols="10" class="pb-0">
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
        </v-row>
      </v-container>

      <!-- <v-spacer></v-spacer> -->
    </v-app-bar>
    <v-main>
      <v-container fluid>
        <nuxt />
      </v-container>
    </v-main>
    <!-- <v-navigation-drawer v-model="rightDrawer" :right="right" temporary fixed>
      <template v-slot:prepend>
        <v-list-item two-line>
          <v-list-item-avatar>
            <v-icon color="primary" size="40">mdi-account-circle</v-icon>
          </v-list-item-avatar>

          <v-list-item-content>
            <v-list-item-title>{{ username }}</v-list-item-title>
            <v-list-item-subtitle>Đã đăng nhập</v-list-item-subtitle>
          </v-list-item-content>
        </v-list-item>
      </template>

      <v-divider></v-divider>

      <v-list dense nav>
        <v-list-item
          v-for="accountMenuItem in accountMenuItems"
          :key="accountMenuItem.title"
          router
          :to="accountMenuItem.to"
          color="primary"
        >
          <v-list-item-icon>
            <v-icon>{{ accountMenuItem.icon }}</v-icon>
          </v-list-item-icon>

          <v-list-item-content>
            <v-list-item-title>{{ accountMenuItem.title }}</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-list-item @click="logout" color="primary">
          <v-list-item-icon>
            <v-icon>mdi-logout</v-icon>
          </v-list-item-icon>

          <v-list-item-content>
            <v-list-item-title>Đăng xuất</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-navigation-drawer> -->
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
    logout() {
      Object.keys(Cookies.get()).forEach(function(cookieName) {
        var neededAttributes = {
          // Here you pass the same attributes that were used when the cookie was created
          // and are required when removing the cookie
        }
        Cookies.remove(cookieName, neededAttributes)
      })
      this.$router.push('/login')
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

.border-bot-mbf .v-toolbar__content {
  background-color: #fc5a31;
  height: 127px !important;
  color: white;
  a {
    color: white;
    font-size: 15px;
    text-decoration: none;
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
</style>
