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
            <a v-if="checkLogIn()" @click="registration">Đăng Ký |</a>
            <a v-if="checkLogIn()" class="pl-1" @click="logIn">Đăng nhập</a>
            <span v-else>
              <v-menu v-model="showMenu" absolute offset-y>
                <template v-slot:activator="{ on, attrs }">
                  <v-avatar size="25" class="avatarCusTom">
                    <img :src="imgA" v-bind="attrs" v-on="on" />
                  </v-avatar>
                  {{
                    username == 'null' || username == 'undefined'
                      ? ''
                      : username
                  }}
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
              <img src="/fashion_trang.png" width="140" />
            </nuxt-link>
          </v-col>
          <v-col cols="8" class="pb-0">
            <v-text-field
              label="Tìm sản phẩm , thương hiệu và tên shop"
              solo
              hide-details
              flat
              dense
              v-model="seach"
            >
            </v-text-field>

            <v-btn
              style="position: absolute;top: 45px;right: 561px;"
              class="ma-2"
              depressed
              color="#FF6633"
              dark
              @click="timkiem"
            >
              <v-icon dark>
                mdi-magnify
              </v-icon>
            </v-btn>
            <div class="list-sp">
              <a
                href=""
                v-for="(item, index) in listName"
                :key="index"
                class="pr-3"
                @click="next(item)"
              >
                {{ item.name }}
              </a>
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
      <v-row
        style="background-color: white ;border-top: 3px solid red; justify-content: center;"
      >
        <v-col
          cols="8"
          style="color: rgba(0,0,0,.54); font-size: .875rem;"
          class="mt-3"
        >
          <strong>
            Fashion - MUA SẮM VÀ BÁN HÀNG ONLINE ĐƠN GIẢN, NHANH CHÓNG VÀ AN
            TOÀN
            <br />
            <br />
          </strong>
          <div style="font-size: .75rem;">
            Nếu bạn đang tìm kiếm một trang web để mua và bán hàng trực tuyến
            thì Fashion.vn là một sự lựa chọn hiệu quả dành cho bạn. Bản chất
            của Fashion là một social ecommerce platform - nền tảng trang web
            thương mại điện tử tích hợp mạng xã hội. Điều này cho phép người mua
            và người bán hàng dễ dàng tương tác, trao đổi thông tin về sản phẩm
            và chương trình khuyến mãi của shop. Nhờ nền tảng đó, việc mua bán
            trên Fashion trở nên nhanh chóng và đơn giản hơn. Bạn có thể trò
            chuyện trực tiếp với nhà bán hàng để hỏi trực tiếp về mặt hàng cần
            mua. Còn nếu bạn muốn tìm mua những dòng sản phẩm chính hãng, uy
            tín, Fashion Mall chính là sự lựa chọn lí tưởng dành cho bạn. Đến
            với Fashion, cơ hội để trở thành một nhà bán hàng dễ dàng hơn bao
            giờ hết. Chỉ với vài thao tác trên ứng dụng, bạn đã có thể đăng bán
            ngay những sản phẩm của mình. Không những thế, các nhà bán hàng có
            thể tùy chọn các tính năng “Shop tạm nghỉ” hoặc tự tạo riêng cho
            mình một chương trình khuyến mãi để thu hút người mua với những sản
            phẩm có mức giá hấp dẫn. Khi đăng nhập tại Fashion Kênh người bán,
            bạn có thể dễ dàng phân loại sản phẩm, theo dõi đơn hàng, chăm sóc
            khách hàng và cập nhập ngay các hoạt động của shop.
            <br />
            <br />
          </div>
          <strong>
            TẢI ỨNG DỤNG Fashion NGAY ĐỂ MUA BÁN ONLINE MỌI LÚC, MỌI NƠI
            <br />
            <br />
          </strong>
          <div style="font-size: .75rem;">
            Ưu điểm của ứng dụng Fashion là cho phép bạn mua và bán hàng mọi
            lúc, mọi nơi. Bạn có thể tải ứng dụng Fashion cũng như đăng sản phẩm
            bán hàng một cách nhanh chóng và tiện lợi. Ngoài ra, ứng dụng
            Fashion còn có những ưu điểm sau: - Giao diện thân thiện, đơn giản,
            dễ sử dụng. Bạn sẽ dễ dàng thấy được ngay những sản phẩm nổi bật
            cũng như dễ dàng tìm đến các ô tìm kiếm, giỏ hàng hoặc tính năng
            chat liền tay. - Ứng dụng tích hợp công nghệ quản lý đơn mua và bán
            hàng tiện lợi trên cùng một tài khoản. Bạn sẽ vừa là người mua hàng,
            vừa là người bán hàng rất linh hoạt, dễ dàng. - Cập nhập thông tin
            khuyến mãi, Fashion flash sale nhanh chóng và liên tục. Tại Fashion,
            bạn có thể lấy các mã khuyến mãi, mã giảm giá Fashion và mã miễn phí
            vận chuyển toàn quốc. Bên cạnh đó, Fashion cũng sẽ có những chiến
            dịch khuyến mãi lớn hằng năm như Fashion 9.9 sale, Fashion 10.10
            sale, Fashion 11.11 sale, Fashion 12.12 sale, Fashion Tết Sale. Đây
            là thời điểm để người mua hàng có thể nhanh tay chọn ngay cho mình
            những mặt hàng ưa thích với mức giá giảm kỉ lục.
            <br />
            <br />
          </div>
          <strong>
            MUA HÀNG CHÍNH HÃNG TỪ CÁC THƯƠNG HIỆU LỚN VỚI Fashion
            <br />
            <br />
          </strong>
          <div style="font-size: .75rem;">
            Mua hàng trên Fashion luôn là một trải nghiệm ấn tượng. Dù bạn đang
            có nhu cầu mua bất kỳ mặt hàng thời trang nam, thời trang nữ, đồng
            hồ, điện thoại, nước rửa tay khô hay khẩu trang N95 thì Fashion cũng
            sẽ đảm bảo cung cấp cho bạn những sản phẩm ưng ý. Bên cạnh đó,
            Fashion cũng có sự tham gia của các thương hiệu hàng đầu thế giới ở
            đa dạng nhiều lĩnh vực khác nhau. Trong đó có thể kể đến Samsung,
            OPPO, Xiaomi, Innisfree, Unilever, P&G, Biti’s,...Các thương hiệu
            này hiện cũng đã có cửa hàng chính thức trên Fashion Mall với hàng
            trăm mặt hàng chính hãng, được cập nhập liên tục. Là một kênh bán
            hàng uy tín, Fashion luôn cam kết mang lại cho khách hàng những trải
            nghiệm mua sắm online giá rẻ, an toàn và tin cậy. Mọi thông tin về
            người bán và người mua đều được bảo mật tuyệt đối. Các hoạt động
            giao dịch thanh toán tại Fashion luôn được đảm bảo diễn ra nhanh
            chóng, an toàn. Một vấn đề nữa khiến cho các khách hàng luôn quan
            tâm đó chính là mua hàng trên Fashion có đảm bảo không. Fashion luôn
            cam kết mọi sản phẩm trên Fashion, đặc biệt là Fashion Mall đều là
            những sản phẩm chính hãng, đầy đủ tem nhãn, bảo hành từ nhà bán
            hàng. Ngoài ra, Fashion bảo vệ người mua và người bán bằng cách giữ
            số tiền giao dịch đến khi người mua xác nhận đồng ý với đơn hàng và
            không có yêu cầu khiếu nại, trả hàng hay hoàn tiền nào. Thanh toán
            sau đó sẽ được chuyển đến cho người bán. Đến với Fashion ngay hôm
            nay để mua hàng online giá rẻ và trải nghiệm dịch vụ chăm sóc khách
            hàng tuyệt vời tại đây. Đặc biệt khi mua sắm trên Fashion Mall, bạn
            sẽ được miễn phí vận chuyển, giao hàng tận nơi và 7 ngày miễn phí
            trả hàng. Ngoài ra, khách hàng có thể sử dụng Fashion Xu để đổi lấy
            mã giảm giá có giá trị cao và voucher dịch vụ hấp dẫn. Tiếp đến là
            Fashion Mum's Club với các ưu đãi độc quyền từ các thương hiệu lớn
            như Enfa; Friso; Pampers...cho các mẹ bỉm sữa đăng ký làm thành
            viên. Hãy truy cập ngay Fashion.vn hoặc tải ngay ứng dụng Fashion về
            điện thoại ngay hôm nay!
          </div>
        </v-col>
      </v-row>
      <v-col
        cols="12"
        class="login-footer"
        style="background-color: #F5F5F5 !important;"
      >
        <v-row class="body-2" style=" color: #707070;">
          <v-col cols="2" class="px-0"></v-col>
          <v-col cols="8" class="pl-0 d-flex">
            <div>
              <div class="pr-2 pb-4">CHĂM SÓC KHÁCH HÀNG</div>
              <div class="caption">
                <div class="pb-2"><a href="">Trung Tâm trợ Giúp</a></div>
                <div class="pb-2"><a href="">Fashion Blog</a></div>
                <div class="pb-2"><a href="">Hướng Dẫn Mua Hàng</a></div>
                <div class="pb-2"><a href="">Hướng Dẫn Bán Hàng</a></div>
                <div class="pb-2"><a href="">Thanh Toán</a></div>
                <div class="pb-2"><a href="">Fashion Xu</a></div>
                <div class="pb-2"><a href="">Vận Chuyển</a></div>
                <div class="pb-2"><a href="">Trả Hàng & Hoàn Tiền</a></div>
                <div class="pb-2"><a href="">Chăm Sóc Khách Hàng</a></div>
                <div class="pb-2"><a href=""> Chính Sách Bảo Hành</a></div>
              </div>
            </div>
            <div class="pl-6">
              <div class="pr-2 pb-4">VỀ Fashion</div>
              <div class="caption">
                <div class="pb-2">
                  <a href="">Giới Thiệu Về Fashion Việt Nam</a>
                </div>
                <div class="pb-2"><a href="">Tuyển Dụng</a></div>
                <div class="pb-2"><a href="">Điều Khoản Fashion</a></div>
                <div class="pb-2"><a href="">Chính Sách Bảo Mật</a></div>
                <div class="pb-2"><a href="">Chính Hãng</a></div>
                <div class="pb-2"><a href="">Kênh Người Bán</a></div>
                <div class="pb-2"><a href="">Flash Sales</a></div>
                <div class="pb-2">
                  <a href="">Chương Trình Tiếp Thị Liên Kết Fashion</a>
                </div>
                <div class="pb-2"><a href="">Liên Hệ Với Truyền Thông</a></div>
              </div>
            </div>
            <div class="pl-6">
              <div class="pl-3 pb-3">THANH TOÁN</div>
              <div>
                <img src="/image_2020_12_31T08_02_06_198Z.png" alt="" />
              </div>
              <div class="pl-3 pb-3">ĐƠN VỊ VẬN CHUYỂN</div>
              <div>
                <img src="/image_2020_12_31T08_02_15_182Z.png" alt="" />
              </div>
            </div>
            <div class="pl-6">
              <div class=" pb-3">THEO DÕI CHÚNG TÔI TRÊN</div>
              <div class="pb-2">Facebook</div>
              <div class="pb-2">Instagram</div>
              <div class="pb-2">LinkedIn</div>
            </div>
            <div class="pl-6">
              <div class=" pb-3">TẢI ỨNG DỤNG Fashion NGAY THÔI</div>
              <div>
                <img src="/image_2020_12_31T08_02_28_720Z.png" alt="" />
              </div>
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
    this.productCategory()

    if (!this.$isServer) {
      if (!this.$store.state.app.getRoles) {
        this.$store.dispatch('app/GetRole')
      }
      this.username = Cookies.get('username')
      this.imgA =
        Cookies.get('avatar') == 'null' || Cookies.get('avatar') == 'undefined'
          ? 'https://static2.yan.vn/YanNews/2167221/202003/dan-mang-du-trend-thiet-ke-avatar-du-kieu-day-mau-sac-tu-anh-mac-dinh-b0de2bad.jpg'
          : Cookies.get('avatar')
    }
  },
  data() {
    return {
      listName: [],
      open_logout: false,
      showMenu: false,
      clipped: true,
      drawer: true,
      fixed: false,
      imgA: null,
      seach: null,
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
    timkiem() {
      this.$router.push(`/detailProduct/timkiem=${this.seach}`)
    },
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
    registration() {
      this.$router.push('/registration')
    },
    productCategory() {
      this.$store.dispatch('listProducts/onTop', {}).then(res => {
        this.listName = res.data.data
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
    next(item) {
      this.$router.push(`/detailProduct/${item.id}`)
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
