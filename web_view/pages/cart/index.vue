<template>
  <div class="cardProduct">
    <v-card flat>
      <v-card-title>
        Giỏ hàng
      </v-card-title>
      <v-card-actions class="px-4">
        <div class="styProduct pa-5">
          <div>Bạn đã chọn 12 sản phẩm</div>
          <v-divider></v-divider>
          <v-row>
            <v-col cols="2" class="d-flex">
              <v-checkbox v-model="ex4" hide-details></v-checkbox>
              <div class="imgProduct">
                <v-img
                  src="https://naidecor.vn/wp-content/uploads/2020/01/ct00192-549k.jpg"
                  width="100"
                  height="90"
                >
                </v-img>
              </div>
            </v-col>
            <v-col cols="2">
              <div>
                (𝐇𝐚̀𝐧𝐠 𝐒𝐢̣𝐧 𝐒𝐡𝐨𝐩) Quần Áo Nam Thu Đông bền đẹp
              </div>
            </v-col>
            <v-col cols="2">
              ₫162.000
            </v-col>
            <v-col cols="2">
              12 sản phẩm
            </v-col>
            <v-col cols="2">
              ₫1,944,000
            </v-col>
            <v-col cols="2">
              <v-btn color="#4873C2" dark depressed width="120px" text>
                Xóa sản phẩm
              </v-btn>
            </v-col>
          </v-row>
          <v-divider></v-divider>
          <v-row>
            <v-col cols="2" class="d-flex">
              <v-checkbox v-model="ex4" hide-details></v-checkbox>
              <div class="imgProduct">
                <v-img
                  src="https://naidecor.vn/wp-content/uploads/2020/01/ct00192-549k.jpg"
                  width="100"
                  height="90"
                >
                </v-img>
              </div>
            </v-col>
            <v-col cols="2">
              <div>
                (𝐇𝐚̀𝐧𝐠 𝐒𝐢̣𝐧 𝐒𝐡𝐨𝐩) Quần Áo Nam Thu Đông bền đẹp
              </div>
            </v-col>
            <v-col cols="2">
              ₫162.000
            </v-col>
            <v-col cols="2">
              12 sản phẩm
            </v-col>
            <v-col cols="2">
              ₫1,944,000
            </v-col>
            <v-col cols="2">
              <v-btn color="#4873C2" dark depressed width="120px" text>
                Xóa sản phẩm
              </v-btn>
            </v-col>
          </v-row>
          <v-divider></v-divider>
          <v-row>
            <v-col cols="2" class="d-flex">
              <v-checkbox v-model="ex4" hide-details></v-checkbox>
              <div class="imgProduct">
                <v-img
                  src="https://naidecor.vn/wp-content/uploads/2020/01/ct00192-549k.jpg"
                  width="100"
                  height="90"
                >
                </v-img>
              </div>
            </v-col>
            <v-col cols="2">
              <div>
                (𝐇𝐚̀𝐧𝐠 𝐒𝐢̣𝐧 𝐒𝐡𝐨𝐩) Quần Áo Nam Thu Đông bền đẹp
              </div>
            </v-col>
            <v-col cols="2">
              ₫162.000
            </v-col>
            <v-col cols="2">
              12 sản phẩm
            </v-col>
            <v-col cols="2">
              ₫1,944,000
            </v-col>
            <v-col cols="2">
              <v-btn color="#4873C2" dark depressed width="120px" text>
                Xóa sản phẩm
              </v-btn>
            </v-col>
          </v-row>
        </div>
      </v-card-actions>
    </v-card>
    <div class="pt-2">
      <v-card flat>
        <v-row>
          <v-col cols="6" class="d-flex">
            <v-checkbox
              class="pl-4 pt-0"
              v-model="ex4"
              hide-details
            ></v-checkbox>
            <div class="mt-4">Chọn tất cả</div>
          </v-col>
          <v-col cols="4" class="mt-3 pr-0">
            <div class="d-flex mt-3">
              <div>Tổng tiền hàng (2 sản phẩm):</div>
              <div
                class="display-1 color-text d-flex"
                style="margin-top: -9px;"
              >
                <div class="title">₫</div>
                99.000
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
            >
              Mua Hàng
            </v-btn>
          </v-col>
        </v-row>
      </v-card>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      ex4: true
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
