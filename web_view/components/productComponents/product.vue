<template>
  <div>
    <v-row>
      <v-col cols="3" class=" pt-0">
        <v-card flat class="cardProduct" @click="detailProduct">
          <v-img
            height="200"
            src="https://thehekhoinghiep.com/wp-content/uploads/2018/07/Cach-chup-anh-quan-ao-ban-hang-online-dep-12.jpg"
          ></v-img>
          <v-card-text class="pa-2 caption black--text"
            >Áo body giữ nhiệt thể thao nam chất vải thun lạnh One
            shop</v-card-text
          >
          <v-card-text class="px-2 py-0 caption black--text d-flex">
            <div class="pr-1" style="margin-top:2px">₫</div>
            <div class="red--text body-1">70.000</div>
          </v-card-text>
          <v-card-text class="px-1 py-1 d-flex">
            <v-icon @click="changeIconHeart" v-if="heart" color="red"
              >mdi-heart</v-icon
            >
            <v-icon @click="changeIconHeart" v-else>mdi-heart-outline</v-icon>
            <v-spacer></v-spacer>
            <v-rating
              :value="4"
              class="ratinga  "
              background-color="orange lighten-3"
              color="orange"
              small
            ></v-rating>
            <div class="caption black--text">Đã bán 448</div>
          </v-card-text>
          <v-card-text class="caption black--text px-1 pt-1" align="end"
            >Hà Nội</v-card-text
          >
        </v-card>
      </v-col>
      <v-col cols="3" class="pt-0">
        <div>
          <v-card flat class="cardProduct">
            <v-img
              height="200"
              src="https://thehekhoinghiep.com/wp-content/uploads/2018/07/Cach-chup-anh-quan-ao-ban-hang-online-dep-12.jpg"
            ></v-img>
            <v-card-text class="pa-2 caption black--text"
              >Áo body giữ nhiệt thể thao nam chất vải thun lạnh One
              shop</v-card-text
            >
            <v-card-text class="px-2 py-0 caption black--text d-flex">
              <div class="pr-1" style="margin-top:2px">₫</div>
              <div class="red--text body-1">70.000</div>
            </v-card-text>
            <v-card-text class="px-1 py-1 d-flex">
              <v-icon @click="changeIconHeart" v-if="heart" color="red"
                >mdi-heart</v-icon
              >
              <v-icon @click="changeIconHeart" v-else>mdi-heart-outline</v-icon>
              <v-spacer></v-spacer>
              <v-rating
                :value="4"
                class="ratinga  "
                background-color="orange lighten-3"
                color="orange"
                small
              ></v-rating>
              <div class="caption black--text">Đã bán 448</div>
            </v-card-text>
            <v-card-text class="caption black--text px-1 pt-1" align="end"
              >Hà Nội</v-card-text
            >
          </v-card>
        </div>
      </v-col>
    </v-row>
  </div>
</template>

<script>
export default {
  // mounted() {
  //   this.$store.dispatch('listProducts/get_list_Products', {})
  // },
  data() {
    return { heart: true }
  },
  methods: {
    changeIconHeart() {
      this.heart = !this.heart
    },
    detailProduct() {
      this.$router.push('/detai')
    }
  }
}
</script>

<style lang="scss" scoped>
.cardProduct {
  border: 1px solid white !important;
}
.cardProduct:hover {
  border: 1px solid #ee4d2d !important;
  transition: all 0.5s;
  margin-top: -10px;
}
</style>
