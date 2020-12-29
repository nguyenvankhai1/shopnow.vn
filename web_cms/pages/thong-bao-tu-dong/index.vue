<template>
  <div v-if="checkRole()">
    <div style="background-color: #f4f7fe">
      <v-container>
        <v-row>
          <v-col cols="12" class="py-0 d-flex">
            <v-col cols="3" class="px-0">
              <div>
                <v-card-text class="font-weight-bold fs-16"
                  >Đăng nhập</v-card-text
                >
                <v-card-text class="font-italic fs-12" style="margin-top: -30px"
                  >Thông báo khách hàng chưa đăng nhập</v-card-text
                >
              </div>
            </v-col>
            <v-col cols="1">
              <div>
                <v-switch
                  v-model="login_noti_status"
                  :false-value="0"
                  :true-value="1"
                  class="py-0"
                  inset
                  color="#47bbff"
                ></v-switch>
              </div>
            </v-col>
            <v-col cols="8"> </v-col>
          </v-col>
          <v-col cols="3"></v-col>
          <v-col cols="8">
            <v-textarea
              v-model="login_noti_content"
              style="margin-top: -40px"
              outlined
              rows="3"
              placeholder="Nhập thông báo"
            ></v-textarea>
          </v-col>
          <v-col cols="11" class="d-flex justify-end">
            <v-menu offset-y>
              <template v-slot:activator="{ on, attrs }">
                <v-btn
                  v-model="login"
                  style="margin-top: -45px"
                  color="blue-grey lighten-5"
                  class="text-none"
                  depressed
                  v-bind="attrs"
                  v-on="on"
                >
                  Placeholder
                </v-btn>
              </template>
              <v-list dense>
                <v-list-item
                  v-for="(item, index) in items"
                  :key="index"
                  @click="patternLogin(item)"
                >
                  <v-list-item-title>{{ item.pattern }}</v-list-item-title>
                </v-list-item>
              </v-list>
            </v-menu>
          </v-col>

          <v-col cols="12" class="py-0 d-flex">
            <v-col cols="3" class="px-0">
              <div>
                <v-card-text class="font-weight-bold fs-16"
                  >Đánh giá/ Trả lời đánh giá có phản hồi</v-card-text
                >
              </div>
            </v-col>
            <v-col cols="1">
              <div>
                <v-switch
                  v-model="reply_comment_noti_status"
                  :false-value="0"
                  :true-value="1"
                  class="py-0"
                  inset
                  color="#47bbff"
                ></v-switch>
              </div>
            </v-col>
            <v-col cols="8"> </v-col>
          </v-col>
          <v-col cols="3"></v-col>
          <v-col cols="8">
            <v-textarea
              v-model="reply_comment_noti_content"
              style="margin-top: -25px"
              outlined
              rows="3"
              placeholder="Nhập thông báo"
            ></v-textarea>
          </v-col>
          <v-col cols="11" class="d-flex justify-end">
            <v-menu offset-y>
              <template v-slot:activator="{ on, attrs }">
                <v-btn
                  v-model="reply"
                  style="margin-top: -45px"
                  color="blue-grey lighten-5"
                  class="text-none"
                  depressed
                  v-bind="attrs"
                  v-on="on"
                >
                  Placeholder
                </v-btn>
              </template>
              <v-list dense>
                <v-list-item
                  v-for="(item, index) in items"
                  :key="index"
                  @click="patternReply(item)"
                >
                  <v-list-item-title>{{ item.pattern }}</v-list-item-title>
                </v-list-item>
              </v-list>
            </v-menu>
          </v-col>

          <v-col cols="11" class="pt-16 d-flex justify-end">
            <v-btn
              color="#FF0F59"
              width="90px"
              class="text-none white--text mr-3"
              depressed
              @click="open_cancel = true"
              >Hủy</v-btn
            >

            <v-btn
              class="white--text text-none"
              width="90px"
              color="#47BBFF"
              depressed
              @click="open_update = true"
              >Lưu</v-btn
            >
          </v-col>
        </v-row>
      </v-container>
      <yes-no-alert
        :open="open_cancel"
        alert-msg="Bạn chắc chắn muốn hủy không?"
        @toggle="open_cancel = !open_cancel"
        @OK="toggle"
      >
      </yes-no-alert>
      <yes-no-alert
        :open="open_update"
        alert-msg="Bạn chắc chắn muốn lưu thay đổi thông báo tự động không?"
        @toggle="open_update = !open_update"
        @OK="save"
      >
      </yes-no-alert>
    </div>
  </div>
</template>
<script>
import Rules from '~/assets/configurations/Rules'
export default {
  data: () => ({
    Rules,
    items: '',
    login: null,
    reply: null,
    open_update: false,
    open_cancel: false,
    login_noti_status: null,
    login_noti_content: null,
    reply_comment_noti_content: null,
    reply_comment_noti_status: null,
  }),
  mounted() {
    this.info()
    this.pattern_list()
    this.$store.commit('app/checkBack', false)
  },
  methods: {
    checkRole() {
      const roles = this.$isAccessAction(
        this.$store.state.app.roles,
        Rules.System.AutomaticNotifications.id
      )
      return roles
    },
    toggle() {
      this.$emit('toggle')
    },
    patternLogin(item) {
      this.login_noti_content += item.pattern
    },
    patternReply(item) {
      this.reply_comment_noti_content += item.pattern
    },
    pattern_list() {
      this.$store.dispatch('thong_bao_tu_dong/autoPattern').then((res) => {
        if (!res.error) {
          this.items = res.data.data
        }
      })
    },
    save() {
      this.$store
        .dispatch('thong_bao_tu_dong/update', {
          login_noti_status: this.login_noti_status,
          login_noti_content: this.login_noti_content,
          reply_comment_noti_status: this.reply_comment_noti_status,
          reply_comment_noti_content: this.reply_comment_noti_content,
        })
        .then((response) => {
          if (!response.error) {
            this.$showSuccess(this, 'Cập nhật thông báo tự động thành công')
          }
        })
    },
    info() {
      this.$store.dispatch('thong_bao_tu_dong/info').then((response) => {
        if (!response.error) {
          this.login_noti_status = response.data.data.login_noti_status
          this.login_noti_content = response.data.data.login_noti_content
          this.reply_comment_noti_content =
            response.data.data.reply_comment_noti_content
          this.reply_comment_noti_status =
            response.data.data.reply_comment_noti_status
        }
      })
    },
  },
  head() {
    this.$store.commit('app/setTitle', 'Thông báo tự động')
    return {
      title: 'Thông báo tự động',
    }
  },
}
</script>
