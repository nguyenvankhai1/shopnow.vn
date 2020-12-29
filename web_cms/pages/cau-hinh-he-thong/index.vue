<template>
  <div v-if="checkRole()">
    <client-only>
      <div>
        <v-autocomplete
          v-model="category"
          :items="list_categories"
          item-value="id"
          item-text="name"
          label="Chọn danh mục"
          no-data-text="Không có dữ liệu"
          class="border-1 fs-14"
          solo
          flat
          dense
        >
        </v-autocomplete>
      </div>
      <v-tabs v-model="tab">
        <v-tab
          v-for="(item, index) in items"
          :key="`${index}-${item}`"
          class="text-none"
        >
          {{ item.lang_name }}
        </v-tab>
        <div class="flex-grow-1"></div>
        <div class="pa-3">
          <v-btn
            color="#47BBFF"
            class="text-none white--text"
            depressed
            @click="open_add = true"
          >
            <v-icon>mdi-plus</v-icon>
            Thêm mới
          </v-btn>
        </div>
      </v-tabs>

      <v-tabs-items v-model="tab">
        <v-tab-item
          v-for="(data, index) in items"
          :key="`${index}-${data.id}`"
          :eager="true"
        >
          <v-data-table
            :headers="headers"
            :items="data.list"
            fixed-header
            disable-pagination
            hide-default-footer
            :disable-sort="true"
            loading-text="Xin chờ..."
            no-results-text="Không có kết quả phù hợp"
            no-data-text="Không có dữ liệu"
            class="elevation-0"
          >
            <template v-slot:[`item.actions`]="{ item }">
              <div class="d-flex">
                <v-tooltip bottom>
                  <template v-slot:activator="{ on, attrs }">
                    <v-btn
                      icon
                      v-bind="attrs"
                      v-on="on"
                      @click="quest_delete(item)"
                    >
                      <icon-delete />
                    </v-btn>
                  </template>
                  <span>Xóa</span>
                </v-tooltip>

                <v-tooltip bottom>
                  <template v-slot:activator="{ on, attrs }">
                    <v-btn
                      icon
                      v-bind="attrs"
                      v-on="on"
                      @click="quest_edit(item)"
                    >
                      <icon-edit />
                    </v-btn>
                  </template>
                  <span>Cập nhật</span>
                </v-tooltip>
              </div>
            </template>
          </v-data-table>
        </v-tab-item>
      </v-tabs-items>

      <yes-no-alert
        :open="open_delete"
        alert-msg="Bạn chắc chắn muốn xóa không?"
        @toggle="open_delete = !open_delete"
        @OK="confirm_delete"
      />

      <Add
        :open="open_add"
        dialog-name="Thêm cấu hình"
        @toggle="open_add = !open_add"
        @add="confirm_add"
      />

      <Edit
        :open="open_edit"
        :data="data_row"
        dialog-name="Cập nhật cấu hình"
        @toggle="open_edit = !open_edit"
        @edit="confirm_edit"
      />
    </client-only>
  </div>
</template>

<script>
import Add from '../../components/ConfigSystem/add'
import Edit from '../../components/ConfigSystem/edit'

import Rules from '~/assets/configurations/Rules'

export default {
  components: {
    Add,
    Edit,
  },
  data: () => ({
    Rules,
    title: 'Cấu hình hệ thống',
    tab: 0,
    items: [],
    data_row: {},
    category: null,
    list_categories: [
      { id: 1, name: 'Danh mục 1' },
      { id: 2, name: 'Danh mục 2' },
      { id: 3, name: 'Danh mục 3' },
      { id: 4, name: 'Danh mục 4' },
    ],
    open_add: false,
    open_delete: false,
    open_edit: false,
    open_confirm: false,
  }),
  computed: {
    headers() {
      return [
        { text: 'ID', value: 'id', width: '15%' },
        { text: 'Giá trị', value: 'name', width: '70%' },
        { text: 'Hành động', value: 'actions', width: '15%' },
      ]
    },
  },
  mounted() {
    this.$store.commit('app/checkBack', false)
    this.set_list()
    this.category = this.list_categories[0].id
  },
  methods: {
    checkRole() {
      const roles = this.$isAccessAction(
        this.$store.state.app.roles,
        Rules.System.Product.id
      )
      return roles
    },
    set_list() {
      this.items = [
        {
          lang_name: 'Tiếng Việt',
          list: [
            { id: 1, name: 'Giá trị 1' },
            { id: 2, name: 'Giá trị 2' },
            { id: 3, name: 'Giá trị 3' },
            { id: 4, name: 'Giá trị 4' },
          ],
        },
        {
          lang_name: 'English',
          list: [
            { id: 1, name: 'Value 1' },
            { id: 2, name: 'Value 2' },
            { id: 3, name: 'Value 3' },
            { id: 4, name: 'Value 4' },
          ],
        },
      ]
    },
    quest_delete(item) {
      this.data_row = item
      this.open_delete = true
    },
    confirm_delete() {},
    quest_edit(item) {
      this.data_row = item
      this.open_edit = true
    },
    confirm_edit(name) {},
    confirm_add(data) {},
  },
  head() {
    this.$store.commit('app/setTitle', this.title)
    return {
      title: this.title,
    }
  },
}
</script>
