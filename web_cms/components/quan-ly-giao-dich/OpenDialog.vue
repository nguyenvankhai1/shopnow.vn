<template>
  <div>
    <v-dialog :value="open" max-width="700px" @click:outside="toggle">
      <v-card>
        <v-card-title class="pt-3 pb-1">Tìm kiếm nâng cao</v-card-title>
        <v-container class="py-0">
          <v-row>
            <v-col cols="6">
              <v-text-field
                v-model="cust_name"
                label="Khách hàng"
                class="border-1 fs-14"
                solo
                flat
                dense
                hide-details
                clearable
              ></v-text-field>
            </v-col>

            <v-col cols="6">
              <v-text-field
                v-model="order_id_mask"
                label="Mã giao dịch"
                class="border-1 fs-14"
                solo
                flat
                dense
                hide-details
                clearable
              ></v-text-field>
            </v-col>

            <v-col cols="12">
              <DateRangePicker
                :label="['Từ ngày', 'Đến ngày']"
                :date="filter.date"
              ></DateRangePicker>
            </v-col>
            <v-col cols="6">
              <v-autocomplete
                v-model="order_status"
                label="Trạng thái"
                :items="list_status"
                class="border-1 fs-14"
                solo
                flat
                dense
                hide-details
                clearable
              >
                <template v-slot:selection="data">
                  <div :style="`color: ${data.item.color}`">
                    {{ data.item.text }}
                  </div>
                </template>
                <template v-slot:item="data">
                  <div
                    :style="`color: ${data.item.color} !important`"
                    class="fs-13"
                  >
                    {{ data.item.text }}
                  </div>
                </template>
              </v-autocomplete>
            </v-col>
          </v-row>
        </v-container>
        <v-card-actions class="pt-6">
          <div class="flex-grow-1"></div>
          <v-btn
            depressed
            color="#47BBFF"
            class="text-none white--text mr-1"
            width="90"
            @click="save"
          >
            <div class="font_size">Lưu</div>
          </v-btn>
          <v-btn depressed class="text-none error" width="90" @click="toggle">
            <div class="font_size">Hủy</div>
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>
import moment from 'moment'
export default {
  components: {},
  props: {
    open: {
      type: Boolean,
      required: true,
    },
  },
  data: () => ({
    filter: {
      date: [
        moment().subtract(7, 'days').format('YYYY-MM-DD'),
        moment().format('YYYY-MM-DD'),
      ],
    },
    cust_name: null,
    order_id_mask: null,
    order_status: null,
    list_status: [
      { value: 0, text: 'Chờ xác nhận', color: '#F5972D', order_status: 0 },
      {
        value: 1,
        text: 'Từ chối xác nhận',
        color: '#FF0F59',
        order_status: 1,
      },
      {
        value: 2,
        text: 'Đã xác nhận - Chưa thanh toán',
        color: '#3F69B8',
        order_status: 2,
      },
      { value: 3, text: 'Đã thanh toán', color: '#17E555', order_status: 3 },
      { value: 4, text: 'Đang giao hàng', color: '#0000FF', order_status: 4 },
      { value: 5, text: 'Đang hủy', color: '#F6CECE', order_status: 5 },
      { value: 6, text: 'Đã hủy', color: '#FF0000', order_status: 6 },
      { value: 7, text: 'Thành công', color: '#00FFFF', order_status: 7 },
      { value: 8, text: 'Đã đóng', color: '#DF013A', order_status: 8 },
    ],
  }),
  methods: {
    save() {
      const data = {
        customer_name:
          (this.cust_name || []).length !== 0 ? this.cust_name.trim() : '',
        order_id_mask:
          (this.order_id_mask || []).length !== 0 ? this.order_id_mask : null,
        order_status: !this.$isNullOrEmpty(this.order_status)
          ? this.order_status
          : null,
        start_time: this.$ymdToD(this.filter.date[0]) + ' 00:00:00',
        stop_time: this.$ymdToD(this.filter.date[1]) + ' 23:59:59',
      }
      this.$emit('filter', data)
      this.$emit('toggle')
    },
    toggle() {
      this.$emit('toggle')
    },
  },
}
</script>
