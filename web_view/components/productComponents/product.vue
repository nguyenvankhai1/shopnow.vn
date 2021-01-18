<template>
  <div v-if="(data || []).length !== 0" class="product">
    <v-row>
      <v-col cols="3" class=" pt-0" v-for="(item, index) in data" :key="index">
        <v-card class="cardProduct" @click="detailProduct(item)">
          <v-img height="200" :src="item.thumbnail"></v-img>
          <v-card-text class="pa-2 caption black--text" style="height: 57px;">{{
            item.name
          }}</v-card-text>
          <v-card-text class="px-2 py-0 caption black--text d-flex">
            <div class="pr-1" style="margin-top:2px">₫</div>
            <div class="red--text body-1">
              {{ $formatMoney({ amount: item.price }) }}
            </div>
            <v-spacer></v-spacer>
            <div class="caption black--text">
              {{ item.voteScore }}({{ item.voteNumber }})
            </div>
          </v-card-text>

          <v-card-text class="px-1 py-1 d-flex">
            <v-icon
              v-if="item.status === 1"
              style="font-size: 14px;"
              color="red"
              >mdi-heart</v-icon
            >
            <v-icon v-else style="font-size: 14px;">mdi-heart-outline</v-icon>
            <v-spacer></v-spacer>
            <v-rating
              :value="item.ranking"
              style="margin-top: -3px;"
              class="ratinga  "
              background-color="orange lighten-3"
              color="orange"
              small
            ></v-rating>

            <div class="caption black--text">Đã bán {{ item.sellNumber }}</div>
          </v-card-text>
          <v-card-text class="caption pa-0 black--text pr-1" align="end"
            >Hà Nội</v-card-text
          >
        </v-card>
      </v-col>
    </v-row>
  </div>
</template>

<script>
export default {
  // mounted() {
  //   this.$store.dispatch('listProducts/get_list_Products', {})
  // },
  props: {
    data: {
      type: Array,
      default: []
    }
  },
  data() {
    return { heart: true }
  },
  methods: {
    detailProduct(item) {
      this.$router.push(`/detai/${item.id}`)
    }
  }
}
</script>

<style lang="scss">
.product {
  .cardProduct {
    border: 1px solid white !important;
  }
  .cardProduct:hover {
    border: 1px solid #ee4d2d !important;
    transition: all 0.5s;
    margin-top: -10px;
  }

  button.v-icon.notranslate.v-icon--link.mdi.mdi-star.theme--light.orange--text {
    font-size: 13px !important;
  }
}
</style>
