<template>
  <div class="login">
    <v-row>
      <v-col cols="6" align="center">
        <div>
          <img
            style="margin-left: -228px; cursor: pointer;"
            src="/fashion_cam.png"
            width="100"
            height="42"
            @click="$router.push('/')"
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
                <v-row class="pb-4">
                  <v-col cols="12" class="px-12" v-if="authentically">
                    <v-card-text class="headline py-3 pl-0"
                      >Đăng ký</v-card-text
                    >
                    <v-text-field
                      outlined
                      dense
                      v-model="username"
                      label="Email/Số điện thoại/Tên đăng nhập"
                      :error-messages="usernameError"
                      @input="usernameError = []"
                    ></v-text-field>
                    <v-text-field
                      id="password"
                      label="Mật khẩu *"
                      :append-icon="showPass ? 'mdi-eye' : 'mdi-eye-off'"
                      dense
                      name="password"
                      v-model="password"
                      :type="showPass ? 'text' : 'password'"
                      :error-messages="passwordError"
                      @input="passwordError = []"
                      outlined
                      @click:append="showPass = !showPass"
                    ></v-text-field>
                    <v-text-field
                      label="Nhập lại mật khẩu *"
                      :append-icon="showPassNew ? 'mdi-eye' : 'mdi-eye-off'"
                      dense
                      name="password"
                      v-model="passwordNew"
                      :type="showPassNew ? 'text' : 'password'"
                      :error-messages="passwordNewError"
                      @input="passwordNewError = []"
                      outlined
                      @click:append="showPassNew = !showPassNew"
                    ></v-text-field>
                    <div class="d-flex justify-center">
                      <v-btn
                        color="primary"
                        @click="LogIn"
                        :loading="$wait.is('logging')"
                        block
                        depressed
                        >Đăng Ký</v-btn
                      >
                    </div>
                    <v-row class="pt-2">
                      <v-col cols="5"><v-divider></v-divider></v-col>
                      <v-col
                        cols="2"
                        align="center"
                        style="opacity: 0.5;"
                        class="px-0 pt-0"
                        >Hoặc</v-col
                      >
                      <v-col cols="5"><v-divider></v-divider></v-col>

                      <v-col
                        cols="7"
                        class="pr-0"
                        style="opacity: 0.5;"
                        align="end"
                        >Bạn đã có tài khoản ?
                      </v-col>
                      <v-col cols="5" class="pl-1">
                        <a @click="$router.push('/login')" style="opacity: 1; "
                          >Đăng Nhập</a
                        >
                      </v-col>
                    </v-row>
                  </v-col>
                  <v-col cols="12" v-else>
                    <v-card-text class="headline py-3 pt-0" align="center"
                      >Nhập mã xác thực</v-card-text
                    >
                    <div class="pl-5">
                      <v-otp-input
                        ref="otpInput"
                        input-classes="otp-input"
                        separator="-"
                        :num-inputs="6"
                        :should-auto-focus="true"
                        :is-input-num="true"
                        @on-change="handleOnChange"
                      />
                    </div>
                    <div class="d-flex justify-center px-12 pt-3">
                      <v-btn color="primary" @click="accuracy" block depressed
                        >Xác thực</v-btn
                      >
                    </div>
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
import OtpInput from '@bachdgvn/vue-otp-input'
import Vue from 'vue'
Vue.component('v-otp-input', OtpInput)
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
    showPass: false,
    showPassNew: false,
    passwordNew: null,
    password: null,
    username: null,
    authentically: true,
    value: null,
    passwordError: [],
    usernameError: [],
    passwordNewError: []
  }),

  methods: {
    accuracy() {
      this.$store
        .dispatch('products/accuracyOTP', {
          email: this.username,
          otp: this.value
        })
        .then(res => {
          console.log(res)
          if (res.data.error.code === 0) {
            this.$showSuccess(this, 'Đăng ký tài khoản thành công')
            this.$router.push('/login')
            this.$router.app.$notify({
              group: 'login',
              type: 'warn',
              text: 'Đăng ký tài khoản thành công'
            })
          } else if (res.data.error.code === Number(APIs.responses.OK.code)) {
            this.$router.app.$notify({
              group: 'login',
              type: 'error',
              text: APIs.responses[errorCode].message
            })
          }
        })
    },
    handleOnChange(value) {
      this.value = value
      // console.log(value)
    },
    LogIn() {
      this.$wait.start('logging')
      let hasErrors = false
      if (this.$isNullOrEmpty(this.username)) {
        hasErrors = true
        this.usernameError = ['Không được để trống']
        this.$wait.end('logging')
      } else if (!this.$isEmail(this.username)) {
        hasErrors = true
        this.usernameError = ['Email không đúng định dạng']
        this.$wait.end('logging')
      }
      if (this.$isNullOrEmpty(this.password)) {
        hasErrors = true
        this.passwordError = ['Không được để trống']
        this.$wait.end('logging')
      } else if (this.password.length < 8) {
        hasErrors = true
        this.passwordError = ['Mật khẩu phải lớn hơn 8 ký tự']
        this.$wait.end('logging')
      }

      if (this.$isNullOrEmpty(this.passwordNew)) {
        hasErrors = true
        this.passwordNewError = ['Không được để trống']
        this.$wait.end('logging')
      }
      if (this.passwordNew !== this.password) {
        hasErrors = true
        this.passwordNewError = ['Mật khẩu không khớp']
        this.$wait.end('logging')
      }

      if (!hasErrors) {
        this.ApiAccuracy()
      }
    },
    ApiAccuracy() {
      this.$store
        .dispatch('login/registerAccount', {
          email: this.username,
          password: this.password
        })
        .then(res => {
          this.$wait.end('logging')
          if (res.data.error.code === 13) {
            this.usernameError = ['Email đã tồn tại']
          }
          if (res.data.error.code === 0) {
            this.authentically = false
          } else if (res.data.error.code === Number(APIs.responses.OK.code)) {
            this.authentically = false
            this.$router.app.$notify({
              group: 'login',
              type: 'error',
              text: APIs.responses[errorCode].message
            })
          }
        })
    }
  },

  computed: {}
}
</script>
<style lang="scss">
.body-login {
  background-color: #ee4d2d;
}
a {
  text-decoration: none;
  color: #707070;
}
.authenticallyNumber {
  .v-text-field__slot {
    input {
      text-align: center;
      margin-left: 12px;
      font-size: 36px;
    }
  }
}
.login {
  margin-top: -127px;
}
.otp-input {
  width: 40px;
  height: 40px;
  padding: 5px;
  margin: 0 10px;
  font-size: 20px;
  border-radius: 4px;
  border: 1px solid rgba(0, 0, 0, 0.3);
  text-align: center;
  &.error {
    border: 1px solid red !important;
  }
}
.otp-input::-webkit-inner-spin-button,
.otp-input::-webkit-outer-spin-button {
  -webkit-appearance: none;
  margin: 0;
}
</style>
