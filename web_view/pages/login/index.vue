<template>
  <div>
    <v-row>
      <v-col cols="6" align="center">
        <div>
          <img
            style="margin-left: -228px;"
            src="/fashion_cam.png"
            width="100"
            height="42"
          />
        </div>
      </v-col>
      <v-col cols="6" align="center">
        <div class="pt-3">
          <a href="" style="text-decoration: none; color: red; font-size: 14px;"
            >Cần trợ giúp</a
          >
        </div>
      </v-col>
      <v-col cols="12" class="body-login">
        <v-container>
          <v-row>
            <v-col cols="6" class="pt-0" align="end">
              <img
                style="margin-right: 111px;margin-top: -23px;"
                src="anh_1.png"
                alt=""
            /></v-col>
            <v-col cols="3">
              <v-card flat class="mt-12">
                <v-row>
                  <v-col class="px-12">
                    <v-container class=" pt-3">
                      <form @submit.prevent="submit">
                        <v-card-text class="headline py-3 pl-0"
                          >Đăng nhập</v-card-text
                        >
                        <v-text-field
                          outlined
                          dense
                          v-model="username"
                          :error-messages="$store.state.login.username.errors"
                          label="Email/Số điện thoại/Tên đăng nhập"
                        ></v-text-field>
                        <v-text-field
                          id="password"
                          label="Mật khẩu *"
                          :append-icon="showPass ? 'mdi-eye' : 'mdi-eye-off'"
                          dense
                          name="password"
                          v-model="password"
                          :type="showPass ? 'text' : 'password'"
                          :error-messages="$store.state.login.password.errors"
                          outlined
                          @click:append="showPass = !showPass"
                        ></v-text-field>
                        <div class="d-flex justify-center">
                          <v-btn
                            color="primary"
                            type="submit"
                            :loading="$wait.is('logging')"
                            block
                            depressed
                            >Đăng nhập</v-btn
                          >
                        </div>
                        <v-row>
                          <v-col cols="6">
                            <a href="">Quên mật khẩu</a>
                          </v-col>
                          <v-col cols="6" align="end">
                            <a href="">Đăng nhập với SMS</a>
                          </v-col>
                          <v-col cols="5"><v-divider></v-divider></v-col>
                          <v-col
                            cols="2"
                            align="center"
                            style="opacity: 0.5;"
                            class="px-0 pt-0"
                            >Hoặc</v-col
                          >
                          <v-col cols="5"><v-divider></v-divider></v-col>
                          <v-col cols="4"></v-col>
                          <v-col cols="4"> </v-col>
                          <v-col cols="4"> </v-col>
                          <v-col cols="12" style="opacity: 0.5; " align="center"
                            >Bạn mới biết đến Shopee?</v-col
                          >
                        </v-row>
                      </form>
                    </v-container>
                  </v-col>
                </v-row>
              </v-card>
            </v-col>
          </v-row>
        </v-container>
      </v-col>
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
    </v-row>
  </div>
</template>

<script>
import Cookie from 'js-cookie'
import APIs from '~/assets/configurations/API_Config'

export default {
  name: 'index',
  layout: 'login',
  head() {
    return {
      title: 'Đăng nhập'
    }
  },
  transition(to, from) {
    if (!from) {
      return 'slide-x-transition'
    }
    return 'slide-x-reverse-transition'
  },

  data: () => ({
    logging: false,
    showPass: false
  }),

  methods: {
    submit() {
      this.$wait.start('logging')
      setTimeout(() => {
        this.$store.dispatch('login/Submit', this.$store.state.app.ip)
      }, 1000)
    }
  },

  computed: {
    username: {
      get() {
        return this.$store.state.login.username.value
      },
      set(value) {
        this.$store.dispatch('login/setUsername', value)
      }
    },
    password: {
      get() {
        return this.$store.state.login.password.value
      },
      set(value) {
        this.$store.dispatch('login/setPassword', value)
      }
    }
  }
}
</script>
<style lang="scss" scoped>
.body-login {
  background-color: #ee4d2d;
}
a {
  text-decoration: none;
  color: #707070;
}
</style>
