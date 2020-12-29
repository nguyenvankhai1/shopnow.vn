<template>
  <div class="pr-8 dashboard">
    <div>
      <v-row>
        <v-spacer></v-spacer>
        <v-col cols="2">
          <v-select
            v-model="languages"
            :items="list_languages"
            class="fs-14 border-1"
            solo
            flat
            dense
            hide-details
            placeholder="Tất cả"
          >
            <template v-slot:append>
              <img class="pr-1" src="~/assets/icon/global.svg" />
            </template>
          </v-select>
        </v-col>
        <v-col cols="2">
          <v-select
            class="fs-14 border-1"
            solo
            flat
            dense
            hide-details
            placeholder="Tuần này"
          >
            <template v-slot:append>
              <img class="pr-1" src="~/assets/icon/date.svg" />
            </template>
          </v-select>
        </v-col>
      </v-row>
    </div>
    <div>
      <v-row class="pl-3">
        <v-col cols="3" class="py-0">
          <v-card-text class="pl-0 font-weight-bold fs-17"
            >Thống kê</v-card-text
          >
        </v-col>
      </v-row>
      <v-row class="pl-3">
        <v-col cols="3" class="py-0">
          <v-card elevation="0">
            <v-card-text class="d-flex">
              <div>
                <div class="fs-17 pb-2">Doanh thu</div>
                <div class="fs-24 font-weight-bold">6000 USD</div>
              </div>
              <v-spacer></v-spacer>
              <img src="~/assets/icon/revenue.svg" />
            </v-card-text>
          </v-card>
        </v-col>
        <v-col cols="3" class="py-0">
          <v-card elevation="0">
            <v-card-text class="d-flex">
              <div>
                <div class="fs-17 pb-2">Số giao dịch</div>
                <div class="fs-24 font-weight-bold">1500</div>
              </div>
              <v-spacer></v-spacer>
              <img src="~/assets/icon/transition.svg" />
            </v-card-text>
          </v-card>
        </v-col>
        <v-col cols="3" class="py-0">
          <v-card elevation="0">
            <v-card-text class="d-flex">
              <div>
                <div class="fs-17 pb-2">Khách hàng</div>
                <div class="fs-24 font-weight-bold">15,2K</div>
              </div>
              <v-spacer></v-spacer>
              <img src="~/assets/icon/customer.svg" />
            </v-card-text>
          </v-card>
        </v-col>
        <v-col cols="3" class="py-0">
          <v-card elevation="0">
            <v-card-text class="d-flex">
              <div>
                <div class="fs-17 pb-2">Nhà cung cấp</div>
                <div class="fs-24 font-weight-bold">64</div>
              </div>
              <v-spacer></v-spacer>
              <img src="~/assets/icon/vendor.svg" />
            </v-card-text>
          </v-card>
        </v-col>
      </v-row>
      <v-row class="pl-3">
        <v-col cols="3" class="py-0">
          <v-card-text class="pl-0 font-weight-bold fs-17"
            >Số giao dịch theo ngày</v-card-text
          >
        </v-col>
      </v-row>
      <v-row class="pl-3">
        <v-col cols="12">
          <div style="background: #fff">
            <div style="position: absolute; left: 30px; top: 10px">
              <span class="fs-12">25/05/2019, 09:41</span>
            </div>
            <apexchart
              type="area"
              height="350"
              width="1030"
              :options="chartOptions"
              :series="series"
            >
            </apexchart>
          </div>
        </v-col>
      </v-row>
      <v-row class="pl-3">
        <v-col cols="6">
          <v-row>
            <v-col cols="6" class="py-0">
              <v-card-text class="pl-0 font-weight-bold fs-17 py-0"
                >Nhà cung cấp</v-card-text
              >
            </v-col>
            <v-col cols="6" class="py-0" style="text-align: right">
              <a
                style="text-decoration: none"
                class="fs-14"
                href="/nha-cung-cap"
              >
                Xem chi tiết
              </a>
            </v-col>
          </v-row>
          <v-row>
            <v-col cols="12">
              <v-data-table
                :headers="header_vendor"
                :items="item_vendor"
                hide-default-footer
                loading-text="Xin chờ..."
                no-results-text="Không có kết quả phù hợp"
                no-data-text="Không có dữ liệu"
                fixed-header
                multi-sort
                :sort-desc="true"
              >
                <template v-slot:[`item.nameVendor`]="{ item }">
                  <a style="text-decoration: none" class="fs-14">
                    {{ item.nameVendor }}
                  </a>
                </template>
                <template v-slot:[`item.register_time`]="{ item }">
                  <div>{{ item.time_vendor }}</div>
                </template>
              </v-data-table>
              <pagination
                :page-info="pageInfo"
                :page="page"
                :page-size="pageSize"
                :page-count="pageCount"
                @changePage="changePage"
                @changePageSize="changePageSize"
              >
              </pagination>
            </v-col> </v-row
        ></v-col>
        <v-col cols="6">
          <v-row>
            <v-col cols="6" class="py-0">
              <v-card-text class="pl-0 font-weight-bold fs-17 py-0"
                >Khách hàng</v-card-text
              >
            </v-col>
            <v-col cols="6" class="py-0" style="text-align: right">
              <a
                style="text-decoration: none"
                class="fs-14"
                href="/nha-cung-cap"
              >
                Xem chi tiết
              </a>
            </v-col>
          </v-row>
          <v-row>
            <v-col cols="12">
              <v-data-table
                :headers="header_customer"
                :items="item_customer"
                hide-default-footer
                loading-text="Xin chờ..."
                no-results-text="Không có kết quả phù hợp"
                no-data-text="Không có dữ liệu"
                fixed-header
                multi-sort
                :sort-desc="true"
              >
                <template v-slot:[`item.nameCustomer`]="{ item }">
                  <a style="text-decoration: none" class="fs-14">
                    {{ item.nameCustomer }}
                  </a>
                </template>
                <template v-slot:[`item.register_time`]="{ item }">
                  <div>{{ item.time_customer }}</div>
                </template>
              </v-data-table>
              <pagination
                :page-info="pageInfo"
                :page="page"
                :page-size="pageSize"
                :page-count="pageCount"
                @changePage="changePage"
                @changePageSize="changePageSize"
              >
              </pagination>
            </v-col> </v-row
        ></v-col>
      </v-row>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      page: 1,
      pageCount: 1,
      pageSize: 50,
      pageInfo: {},
      header_vendor: [
        {
          text: 'Tên nhà cung cấp',
          value: 'nameVendor',
          sortable: false,
          width: 180,
        },
        {
          text: 'Thời gian đăng ký',
          value: 'time_vendor',
          sortable: false,
          width: 200,
          align: 'right',
        },
      ],
      item_vendor: [
        { nameVendor: 'Vinh Phan', time_vendor: '25/05/2019 08:43' },
        { nameVendor: 'Dang Nguyen', time_vendor: '25/05/2019 08:50' },
      ],
      header_customer: [
        {
          text: 'Tên khách hàng',
          value: 'nameCustomer',
          sortable: false,
          width: 180,
        },
        {
          text: 'Thời gian đăng ký',
          value: 'time_customer',
          sortable: false,
          width: 200,
          align: 'right',
        },
      ],
      item_customer: [
        { nameCustomer: 'Vinh Phan', time_customer: '25/05/2019 08:43' },
        { nameCustomer: 'Dang Nguyen', time_customer: '25/05/2019 08:50' },
        { nameCustomer: 'Vinh Phan', time_customer: '25/05/2019 08:43' },
        { nameCustomer: 'Dang Nguyen', time_customer: '25/05/2019 08:50' },
        { nameCustomer: 'Vinh Phan', time_customer: '25/05/2019 08:43' },
        { nameCustomer: 'Dang Nguyen', time_customer: '25/05/2019 08:50' },
        { nameCustomer: 'Vinh Phan', time_customer: '25/05/2019 08:43' },
        { nameCustomer: 'Dang Nguyen', time_customer: '25/05/2019 08:50' },
        { nameCustomer: 'Vinh Phan', time_customer: '25/05/2019 08:43' },
        { nameCustomer: 'Dang Nguyen', time_customer: '25/05/2019 08:50' },
        { nameCustomer: 'Vinh Phan', time_customer: '25/05/2019 08:43' },
        { nameCustomer: 'Dang Nguyen', time_customer: '25/05/2019 08:50' },
        { nameCustomer: 'Vinh Phan', time_customer: '25/05/2019 08:43' },
        { nameCustomer: 'Dang Nguyen', time_customer: '25/05/2019 08:50' },
        { nameCustomer: 'Vinh Phan', time_customer: '25/05/2019 08:43' },
        { nameCustomer: 'Dang Nguyen', time_customer: '25/05/2019 08:50' },
        { nameCustomer: 'Vinh Phan', time_customer: '25/05/2019 08:43' },
        { nameCustomer: 'Dang Nguyen', time_customer: '25/05/2019 08:50' },
        { nameCustomer: 'Vinh Phan', time_customer: '25/05/2019 08:43' },
        { nameCustomer: 'Dang Nguyen', time_customer: '25/05/2019 08:50' },
        { nameCustomer: 'Vinh Phan', time_customer: '25/05/2019 08:43' },
        { nameCustomer: 'Dang Nguyen', time_customer: '25/05/2019 08:50' },
        { nameCustomer: 'Vinh Phan', time_customer: '25/05/2019 08:43' },
        { nameCustomer: 'Dang Nguyen', time_customer: '25/05/2019 08:50' },
        { nameCustomer: 'Vinh Phan', time_customer: '25/05/2019 08:43' },
        { nameCustomer: 'Dang Nguyen', time_customer: '25/05/2019 08:50' },
        { nameCustomer: 'Vinh Phan', time_customer: '25/05/2019 08:43' },
        { nameCustomer: 'Dang Nguyen', time_customer: '25/05/2019 08:50' },
        { nameCustomer: 'Vinh Phan', time_customer: '25/05/2019 08:43' },
        { nameCustomer: 'Dang Nguyen', time_customer: '25/05/2019 08:50' },
        { nameCustomer: 'Vinh Phan', time_customer: '25/05/2019 08:43' },
        { nameCustomer: 'Dang Nguyen', time_customer: '25/05/2019 08:50' },
        { nameCustomer: 'Vinh Phan', time_customer: '25/05/2019 08:43' },
        { nameCustomer: 'Dang Nguyen', time_customer: '25/05/2019 08:50' },
        { nameCustomer: 'Vinh Phan', time_customer: '25/05/2019 08:43' },
        { nameCustomer: 'Dang Nguyen', time_customer: '25/05/2019 08:50' },
        { nameCustomer: 'Vinh Phan', time_customer: '25/05/2019 08:43' },
        { nameCustomer: 'Dang Nguyen', time_customer: '25/05/2019 08:50' },
        { nameCustomer: 'Vinh Phan', time_customer: '25/05/2019 08:43' },
        { nameCustomer: 'Dang Nguyen', time_customer: '25/05/2019 08:50' },
        { nameCustomer: 'Vinh Phan', time_customer: '25/05/2019 08:43' },
        { nameCustomer: 'Dang Nguyen', time_customer: '25/05/2019 08:50' },
        { nameCustomer: 'Vinh Phan', time_customer: '25/05/2019 08:43' },
        { nameCustomer: 'Dang Nguyen', time_customer: '25/05/2019 08:50' },
      ],
      series: [
        {
          name: 'Hôm nay',
          data: [10, 20, 30, 20, 25, 27, 42],
        },
        {
          name: 'Hôm qua',
          data: [11, 32, 45, 32, 34, 42, 41],
        },
      ],
      chartOptions: {
        chart: {
          height: 350,
          type: 'area',
          toolbar: {
            show: false,
          },
        },
        xaxis: {
          labels: {
            show: false,
          },
        },
        dataLabels: {
          enabled: false,
        },
        stroke: {
          curve: 'smooth',
        },
        legend: {
          position: 'top',
          horizontalAlign: 'left',
          offsetX: 120,
          offsetY: 10,
        },
        colors: ['#47BBFF', '#3F69B8'],
      },
      title: 'Dashboard',
      languages: 3,
      list_languages: [
        {
          value: 1,
          text: 'Tiếng việt',
        },
        {
          value: 2,
          text: 'Tiếng anh',
        },
        { value: 3, text: 'Tất cả' },
      ],
    }
  },
  mounted() {
    this.$store.commit('app/checkBack', false)

    if (this.$route.fullPath === '/dashboard') {
      this.$store.commit('app/setTitle', 'Dashboard')
    }
  },
  methods: {
    changePage(value) {
      this.page = value
    },
    changePageSize(pageSizes) {
      if (pageSizes !== this.itemsPerPage) {
        this.page = 1
        this.pageSize = pageSizes
      }
    },
  },
  head() {
    this.$store.commit('app/setTitle', this.title)
    return {
      title: this.title,
    }
  },
}
</script>
<style lang="scss">
.dashboard {
  .v-data-table--fixed-header
    .v-data-table__wrapper
    .v-data-table-header
    th:last-child {
    text-align: right !important;
  }
}
</style>
