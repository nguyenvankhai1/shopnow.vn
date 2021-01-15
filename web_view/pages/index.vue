<template>
  <div>
    <v-row>
      <v-col cols="2">
        <div class="font-weight-bold">
          BỘ LỌC TÌM KIẾM
        </div>
        <div v-for="(item, idx) in items" :key="idx" class="body-2">
          <div class="pt-3">
            {{ item.name }}
          </div>
          <div
            v-for="(checkbox, index) in item.value"
            :key="index"
            class="checkboxCategory"
          >
            <!-- :style="item.add ? 'display: none' : ''" -->
            <v-checkbox
              v-model="checkbox.valueCategory"
              :label="checkbox.nameCategory"
              hide-details
              small
            ></v-checkbox>
          </div>
          <div class="pt-4">
            <v-divider></v-divider>
          </div>
        </div>
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
              small
            ></v-rating>
            <div class="body-2 pt-1">trở lên</div>
          </div>
        </div>
      </v-col>
      <v-col cols="10">
        <productComponents></productComponents>
      </v-col>
    </v-row>
  </div>
</template>

<script>
import productComponents from '~/components/productComponents/product'
export default {
  data() {
    return {
      items: [
        {
          add: false,
          name: 'Theo Danh Mục',
          value: [
            {
              valueCategory: true,
              nameCategory: 'Áo nỉ(80k+)'
            },
            {
              valueCategory: true,
              nameCategory: 'Áo khoác nỉ(80k+)'
            },
            {
              valueCategory: true,
              nameCategory: 'Thời Trang Nữ(80k+)'
            },
            {
              valueCategory: true,
              nameCategory: 'Áo nỉ(80k+)'
            },
            {
              valueCategory: true,
              nameCategory: 'Áo nỉ(80k+)'
            },
            {
              valueCategory: true,
              nameCategory: 'Áo nỉ(80k+)'
            },
            {
              valueCategory: true,
              nameCategory: 'Áo nỉ(80k+)'
            },
            {
              valueCategory: true,
              nameCategory: 'Áo nỉ(80k+)'
            }
          ]
        },
        {
          add: false,
          name: 'Nơi Bán',
          value: [
            {
              valueCategory: true,
              nameCategory: 'Áo nỉ(80k+)'
            },
            {
              valueCategory: true,
              nameCategory: 'Áo nỉ(80k+)'
            },
            {
              valueCategory: true,
              nameCategory: 'Áo nỉ(80k+)'
            },
            {
              valueCategory: true,
              nameCategory: 'Áo nỉ(80k+)'
            }
          ]
        }
      ]
    }
  },
  components: {
    productComponents
  },
  computed: {},
  mounted() {
    this.ListProduct()
  },
  methods: {
    ListProduct() {
      console.log('dsdsd')
      this.$store.dispatch('products/getProduct', {
        productCategoryId: 1,
        pageIndex: 1,
        pageSize: 999,
        orderBy: '1,2'
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
