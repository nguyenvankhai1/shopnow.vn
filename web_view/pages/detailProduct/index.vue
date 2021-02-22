<template>
  <div>
    <v-row>
      <v-col cols="3">
        <div class="font-weight-bold">
          BỘ LỌC TÌM KIẾM
        </div>
        <v-treeview
          v-model="selection"
          @input="search(selection)"
          selectable
          item-disabled="locked"
          :items="items"
        ></v-treeview>
        <div>
          <div class="pt-3">
            Đánh Giá
          </div>
          <div class="d-flex">
            <v-rating
              :value="5"
              class="ratinga"
              background-color="orange lighten-3"
              color="orange"
              readonly
              small
            ></v-rating>
            <div class="body-2 pt-1">trở lên</div>
          </div>
          <div class="d-flex">
            <v-rating
              :value="4"
              class="ratinga"
              background-color="orange lighten-3"
              color="orange"
              readonly
              small
            ></v-rating>
            <div class="body-2 pt-1">trở lên</div>
          </div>
          <div class="d-flex">
            <v-rating
              :value="3"
              class="ratinga"
              background-color="orange lighten-3"
              color="orange"
              readonly
              small
            ></v-rating>
            <div class="body-2 pt-1">trở lên</div>
          </div>
          <div class="d-flex">
            <v-rating
              :value="2"
              class="ratinga"
              background-color="orange lighten-3"
              color="orange"
              readonly
              small
            ></v-rating>
            <div class="body-2 pt-1">trở lên</div>
          </div>
          <div class="d-flex">
            <v-rating
              :value="1"
              class="ratinga"
              background-color="orange lighten-3"
              color="orange"
              readonly
              small
            ></v-rating>
            <div class="body-2 pt-1">trở lên</div>
          </div>
        </div>
      </v-col>
      <v-col cols="9" style="margin-left: -71px;">
        <productComponents :data="dataCategory"></productComponents>
        <div class="text-center">
          <v-pagination
            v-model="page"
            :length="pageSizeTotal"
            prev-icon="mdi-menu-left"
            next-icon="mdi-menu-right"
            @input="nextPage(page)"
          ></v-pagination>
        </div>
      </v-col>
    </v-row>
  </div>
</template>

<script>
import productComponents from '~/components/productComponents/product'
export default {
  data() {
    return {
      selection: [],
      page: 1,
      items: [],
      pageSize: 10,
      pageSizeTotal: null,
      dataCategory: [],
      productCategoryId: 1
    }
  },
  components: {
    productComponents
  },
  mounted() {
    this.ListProduct()
    this.apiCategory()
  },
  methods: {
    search(value) {
      this.productCategoryId = value.join()
      this.ListProduct()
    },
    nextPage(value) {
      this.ListProduct()
    },
    apiCategory() {
      this.$store.dispatch('products/productCategory', {}).then(res => {
        this.items = res.data.data
      })
    },
    ListProduct() {
      console.log(typeof this.productCategoryId, 'this.productCategoryId')
      this.$store
        .dispatch('products/getProduct', {
          productCategoryId: this.productCategoryId,
          pageIndex: this.page,
          pageSize: this.pageSize,
          orderBy: '1,2'
        })
        .then(res => {
          this.dataCategory = res.data.data.content
          this.pageSizeTotal = Math.ceil(res.data.data.total / this.pageSize)
        })
    }
  }
}
</script>

<style lang="scss">
.chart-container {
  width: 100%;
}
.checkboxCategory {
  label {
    color: black !important;
  }
}

.v-input--selection-controls__ripple {
  display: none !important;
}
.ratinga {
  button {
    padding: 2px !important;
  }
}
</style>
