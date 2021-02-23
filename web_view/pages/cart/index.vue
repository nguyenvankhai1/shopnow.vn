<template>
  <div class="cardProduct">
    <v-card flat>
      <v-card-title>
        Giỏ hàng
      </v-card-title>
      <v-card-actions class="px-4" v-if="(data || []).length !== 0">
        <div class="styProduct pa-5">
          <div>Bạn đã chọn {{ data.length }} sản phẩm</div>
          <v-divider></v-divider>
          <div v-for="(item, index) in data" :key="index">
            <v-row>
              <v-col cols="2" class="d-flex">
                <v-checkbox
                  v-model="item.ex4"
                  @change="changecheckbox(item)"
                  hide-details
                ></v-checkbox>
                <div class="imgProduct">
                  <v-img :src="item.avatar" width="100" height="90"> </v-img>
                </div>
              </v-col>
              <v-col cols="2">
                <div>
                  {{ item.name }}
                </div>
              </v-col>
              <v-col cols="2">
                ₫{{ $formatMoney({ amount: item.price }) }}
              </v-col>
              <v-col cols="2"> {{ item.person }} sản phẩm </v-col>
              <v-col cols="2">
                ₫{{
                  $formatMoney({
                    amount: Number(item.person) * Number(item.price)
                  })
                }}
              </v-col>
              <v-col cols="2">
                <v-btn
                  color="#4873C2"
                  dark
                  depressed
                  width="120px"
                  text
                  @click="deletesp(item)"
                >
                  Xóa sản phẩm
                </v-btn>
              </v-col>
            </v-row>
            <v-divider></v-divider>
          </div>
        </div>
      </v-card-actions>
      <v-card-actions class="px-4 text-center" v-else>
        Không có sản phẩm nào
      </v-card-actions>
    </v-card>
    <div class="pt-2">
      <v-card flat>
        <v-row v-if="(data || []).length !== 0">
          <v-col cols="6" class="d-flex"> </v-col>
          <v-col cols="4" class="mt-3 pr-0">
            <div class="d-flex mt-3">
              <div>Tổng tiền hàng ({{ data.length }} sản phẩm):</div>
              <div
                class="display-1 color-text d-flex"
                style="margin-top: -9px;"
              >
                <div class="title">₫</div>
                {{ $formatMoney({ amount: totalMoney }) }}
              </div>
            </div>
          </v-col>
          <v-col cols="2" class="mt-3 pl-0">
            <v-btn
              large
              class="white--text"
              width="180"
              depressed
              color="#F05D40"
              @click="purchase"
            >
              Mua Hàng
            </v-btn>
          </v-col>
        </v-row>
      </v-card>
      <add
        :open="openAdd"
        @toggle="openAdd = !openAdd"
        :dataCart="data"
        :totalMoney="totalMoney"
      ></add>
    </div>
  </div>
</template>

<script>
import add from '~/components/add'
export default {
  components: {
    add
  },
  mounted() {
    this.dataGiohang.push(
      {
        id: 1,
        name: 'sdsjdhdsjhdjs',
        price: 1,
        avatar: 'dsd',
        tinh: 23,
        huyen: 54,
        khuvuc: 64,
        person: 99
      },
      {
        id: 2,
        name: 'sdsjdhdsjhdjs',
        price: 2,
        avatar: 'dsd',
        tinh: 23,
        huyen: 54,
        khuvuc: 64,
        person: 9999
      }
    )
    localStorage.setItem('giohang', JSON.stringify(this.dataGiohang))
    if (!this.$isNullOrEmpty(localStorage.getItem('giohang'))) {
      this.data = JSON.parse(localStorage.getItem('giohang'))
    }
    for (let i = 0; i < this.data.length; i++) {
      Object.assign(this.data[i], { ex4: true })
    }
    this.totalsp()
  },
  data() {
    return {
      openAdd: false,
      ex4: true,
      data: [],
      dataGiohang: [],
      totalMoney: null
    }
  },
  methods: {
    changecheckbox() {
      this.totalsp()
    },
    totalsp() {
      let total = 0
      for (let i = 0; i < this.data.length; i++) {
        if (this.data[i].ex4) {
          total += this.data[i].person * this.data[i].price
        }
      }
      this.totalMoney = total
    },
    purchase() {
      if (!this.$isNullOrEmpty(Cookies.get('token'))) {
        this.openAdd = !this.openAdd
      } else {
        this.$router.push('/login')
      }
    },
    deletesp(value) {
      for (let i = 0; i < this.data.length; i++) {
        if (value.id === this.data[i].id) {
          this.data.splice(i, 1)
        }
      }
      localStorage.setItem('giohang', JSON.stringify(this.data))
      this.totalsp()
    }
  }
}
</script>

<style lang="scss" scoped>
.cardProduct {
  .styProduct {
    width: 100%;
    border: 1px solid rgba(0, 0, 0, 0.09);
  }
  .color-text {
    color: #ee4d2d;
  }
}
</style>
