<template>
  <!-- v-if="checkRole()" -->
  <div v-if="checkRole()" class="main_category_post">
    <div v-if="action === 1" class="page page--table">
      <div class="mr-4">
        <v-row>
          <v-col>
            <v-text-field
              v-model="search"
              placeholder="Tìm kiếm"
              prepend-inner-icon="mdi-magnify"
              class="border-1"
              solo
              flat
              dense
              hide-details
              @keydown.enter="get_list"
            >
            </v-text-field>
          </v-col>
          <v-col cols="auto" class="px-0">
            <v-btn
              color="#47BBFF"
              class="text-none white--text mr-2"
              depressed
              @click="get_list"
            >
              Tìm kiếm
            </v-btn>
            <v-btn
              color="#47BBFF"
              class="text-none white--text"
              depressed
              @click="action = 2"
            >
              <v-icon>mdi-plus</v-icon>Thêm mới
            </v-btn>
          </v-col>
        </v-row>
      </div>
      <v-data-table
        :headers="headers"
        :items="items"
        :page.sync="page"
        :items-per-page="pageSize"
        hide-default-footer
        loading-text="Xin chờ..."
        no-results-text="Không có kết quả phù hợp"
        no-data-text="Không có dữ liệu"
        fixed-header
        multi-sort
        :sort-desc="true"
        class="elevation-0 page__table"
      >
        <template v-slot:[`item.enable_status`]="{ item }">
          <div class="d-flex justify-start align-center" style="width: 120px">
            <v-select
              v-model="item.enable_status"
              :items="status_on_off"
              dense
              solo
              flat
              background-color="#f9f9f9"
              class="mt-5 border-1"
              @change="confirm_change_status(item)"
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
            </v-select>
          </div>
        </template>
        <template v-slot:[`item.page_type`]="{ item }">
          <v-chip v-if="item.page_type === 1" outlined>Trang chủ </v-chip>
          <v-chip v-if="item.page_type === 2" outlined>Trang cơ bản</v-chip>
        </template>

        <!-- //chức năng -->
        <template v-slot:[`item.actions`]="{ item }">
          <div class="d-flex">
            <!-- @click="openDetail(item)" -->
            <v-tooltip v-if="item.is_system !== 1" bottom>
              <template v-slot:activator="{ on, attrs }">
                <v-btn
                  icon
                  v-bind="attrs"
                  v-on="on"
                  @click="quest_delete(item)"
                >
                  <icon-delete></icon-delete>
                </v-btn>
              </template>
              <span>Xóa</span>
            </v-tooltip>
            <div>
              <v-tooltip v-if="item.page_type === 2" bottom>
                <template v-slot:activator="{ on, attrs }">
                  <v-btn
                    icon
                    v-bind="attrs"
                    v-on="on"
                    @click="quest_edit(item)"
                  >
                    <icon-edit></icon-edit>
                  </v-btn>
                </template>
                <span>Cập nhật</span>
              </v-tooltip>
              <v-tooltip v-if="item.page_type === 1" bottom>
                <template v-slot:activator="{ on, attrs }">
                  <v-btn
                    icon
                    v-bind="attrs"
                    v-on="on"
                    @click="quest_edit_page(item)"
                  >
                    <icon-edit></icon-edit>
                  </v-btn>
                </template>
                <span>Cập nhật</span>
              </v-tooltip>
            </div>
            <!-- <v-tooltip bottom>
              <template v-slot:activator="{ on, attrs }">
                <v-btn
                  icon
                  v-bind="attrs"
                  v-on="on"
                  @click="quest_privew(item)"
                >
                  <img src="~/assets/icon/change_color_view.svg" />
                </v-btn>
              </template>
              <span>Xem trước</span>
            </v-tooltip> -->
            <!-- @click="openEdit(item)" -->
          </div>
        </template>
      </v-data-table>

      <pagination
        :items="items"
        :server="false"
        :page-info="pageInfo"
        :page="page"
        :page-size="pageSize"
        :page-count="pageCount"
        @changePage="changePage"
        @changePageSize="changePageSize"
      >
      </pagination>
    </div>

    <add
      v-show="action === 2"
      :open="action"
      @success="get_list"
      @toggle="action = 1"
    ></add>
    <Edit
      v-show="action === 3"
      :data="item_data"
      :open="action"
      @success="get_list"
      @toggle="action = 1"
    ></Edit>
    <EditPage
      v-show="action === 4"
      :data="item_data"
      :open="action"
      @success="get_list"
      @toggle="action = 1"
    ></EditPage>

    <yes-no-alert
      :open="open_delete"
      :alert-msg="alertdelete"
      @toggle="open_delete = !open_delete"
      @OK="confirm_delete"
    >
    </yes-no-alert>
    <yes-no-alert
      :open="open_change"
      alert-msg="Bạn có muốn thay đổi trạng thái không?"
      @toggle="toggle_change_status"
      @OK="change_status"
    >
    </yes-no-alert>
    <yes-no-alert
      :open="open_confirm"
      alert-msg="Bạn có chắc chắn muốn thoát không?"
      @toggle="open_confirm = !open_confirm"
      @OK="action = 1"
    ></yes-no-alert>
  </div>
</template>

<script>
import Add from '~/components/quan-tri-trang/Add'
import Edit from '~/components/quan-tri-trang/Edit'
import EditPage from '~/pages/trang/index.vue'
import Rules from '~/assets/configurations/Rules'
export default {
  components: {
    Add,
    Edit,
    EditPage,
    // Draggable,
  },
  data: () => ({
    Rules,
    search: '',
    open_delete: false,
    open_confirm: false,
    title: 'Quản trị trang',
    status_on_off: [
      { value: 1, text: 'Hiển thị', color: '#47BBFF', status: 1 },
      { value: 0, text: 'Ẩn', color: '#FF0F59', status: 0 },
    ],
    open_change: false,
    items: [],
    page: 1,
    pageSize: 50,
    pageCount: 1,
    pageInfo: {},
    action: 1,
    // itemPerPage: 0,
    alertdelete: '',
    data_row: {},
    status_data: {},

    item_data: {},
    item_detail: {},
  }),
  computed: {
    headers() {
      return [
        {
          text: 'ID',
          sortable: false,
          value: 'id',
          width: 50,
        },
        {
          text: 'Tiêu đề trang',
          sortable: false,
          value: 'name',
          width: 200,
        },
        {
          text: 'Loại trang',
          sortable: false,
          value: 'page_type',
          width: 200,
        },
        {
          text: 'Người sửa gần nhất',
          sortable: false,
          value: 'update_by',
          width: 150,
        },
        {
          text: 'Thời gian cập nhật gần nhất',
          sortable: false,
          value: 'update_time',
          width: 150,
        },

        {
          text: 'Hiển thị/Ẩn',
          sortable: false,
          value: 'enable_status',
          width: 100,
        },
        { text: 'Hành động', sortable: false, value: 'actions', width: 100 },
      ]
    },
  },

  watch: {
    action(value) {
      if (value === 1) {
        this.title = 'Quản trị trang'
        this.$store.commit('app/checkBack', false)
        this.$store.commit('app/setTitle', this.title)
      } else if (value === 2) {
        this.$store.commit('app/setTitle', this.title)
        this.title = 'Thêm trang'
      } else if (value === 3) {
        this.$store.commit('app/setTitle', this.title)
        this.title = `Cập nhật trang`
      } else if (value === 4) {
        this.$store.commit('app/setTitle', this.title)
        this.title = `${this.data_row.name}`
      }
      // else if (value === 4) {
      //   this.title = `Trang - ${this.data_row.tilte}`
      // }
    },
    '$store.state.app.change_back'(value) {
      if (this.$store.state.app.pageTitle.includes('Thêm trang')) {
        this.action = 1
      }
      if (this.$store.state.app.pageTitle.includes('Cập nhật trang')) {
        this.open_confirm = true
      }
      if (this.$store.state.app.pageTitle.includes(`${this.data_row.name}`)) {
        this.open_confirm = true
      }
    },
  },
  created() {
    if (this.$route.fullPath === '/quan-tri-trang') {
      this.title = 'Quản trị trang'
      this.$store.commit('app/setTitle', 'Quản trị trang')
    }
  },
  mounted() {
    if (!this.$isServer) {
      this.get_list()
      this.$store.commit('app/checkBack', false)
    }
  },
  methods: {
    checkRole() {
      const roles = this.$isAccessAction(
        this.$store.state.app.roles,
        Rules.System.Page.id
      )
      return roles
    },
    confirm_change_status(item) {
      this.status_data = item
      this.open_change = true
    },
    toggle_change_status() {
      this.open_change = !this.open_change
      this.get_list()
    },
    change_status() {
      this.$store
        .dispatch('quan_tri_trang/page_update_status', {
          page_id: this.status_data.id,
          enable_status: this.status_data.enable_status,
        })
        .then((response) => {
          if (!response.error) {
            this.$showSuccess(
              this,
              // `Thay đổi trạng thái danh mục "${this.update_status.tilte}" thành công`
              'Thay đổi trạng thái trang thành công'
            )
          }
        })
        .finally(() => {
          this.get_list()
        })
    },

    changePageSize(pageSizes) {
      if (pageSizes !== this.itemsPerPage) {
        this.page = 1
        this.pageSize = pageSizes
        this.get_list()
      }
    },
    changePage(value) {
      this.page = value
      this.get_list()
    },

    get_list() {
      this.$store
        .dispatch('quan_tri_trang/page_list', {
          search: (this.search || []).length !== 0 ? this.search.trim() : '',
          pageIdx: this.page,
          pageSize: this.pageSize,
          orderBy: '',
        })
        .then((response) => {
          if (!response.error) {
            this.items = response.data.data.items

            this.pageCount = response.data.data.pageable.total_page
            const arr = {
              begin_idx: response.data.data.pageable.begin_idx,
              end_idx: response.data.data.pageable.end_idx,
              total_page: response.data.data.pageable.total_page,
              total_record: response.data.data.pageable.total_record,
            }
            this.pageInfo = arr
          }
        })
    },
    quest_delete(item) {
      // this.$log.debug(item)

      this.data_row = item

      this.alertdelete = `Bạn chắc chắn muốn xóa trang " ${item.name} " này?`
      this.open_delete = true
    },
    confirm_delete() {
      this.$store
        .dispatch('quan_tri_trang/page_delete', { page_id: this.data_row.id })
        .then((response) => {
          if (!response.error) {
            this.$showSuccess(this, 'Xóa trang thành công')
            this.get_list()
          }
        })
    },
    quest_edit_page(item) {
      this.data_row = item
      this.$store
        .dispatch('quan_tri_trang_chu/config_detail', { id: this.data_row.id })

        .then((res) => {
          if (!res.error) {
            this.item_data = res.data.data
            this.action = 4
          }
        })
    },
    quest_edit(item) {
      this.data_row = item
      this.$store
        .dispatch('quan_tri_trang/page_detail', this.data_row.id)

        .then((res) => {
          if (!res.error) {
            this.item_data = res.data.data
            this.action = 3
          }
        })
    },
    quest_privew(item) {
      // this.data_row = item
      // this.$store
      //   .dispatch('danh_muc_bai_viet/detail_post', item.id)
      //   .then((res) => {
      //     if (!res.error) {
      //       this.item_detail = res.data.data
      //       this.action = 4
      //     }
      //   })
    },
    // askForDeleteItem(value) {
    //   this.dataDetail = value
    //   this.open_delete = true
    // },
    clearSelected() {
      this.selected = null
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
.page--table {
  .page {
    &__table {
      margin-top: 20px;
    }

    &__grab-icon {
      cursor: move;
    }
  }
}
.main_category_post {
  .v-data-table--fixed-header .v-data-table__wrapper {
    max-height: calc(94vh - 220px) !important;
  }
}
</style>
