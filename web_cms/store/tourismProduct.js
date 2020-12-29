import Cookies from 'js-cookie'
const state = () => ({
  APIs: {
    getProdAttrSetFields: {
      url: 'cms/attribute_set/detail',
      method: 'GET',
    },
    getSku: {
      url: 'cms/product/gen/sku',
      method: 'GET',
    },
    addTourismProduct: {
      url: 'cms/product/add',
      method: 'GET',
    },
  },
})

const actions = {
  // Hàm gọi API lấy các trường trông bộ thuộc tính
  async getProdAttrSetFields(vueContext, payload) {
    const response = await this.$axios({
      ...vueContext.state.APIs.getProdAttrSetFields,
      headers: {
        Authorization: Cookies.get('token'),
      },
      params: {
        ...payload,
      },
    })

    return response
  },

  // Hàm gọi API lấy SKU
  async getSku(vueContext, payload) {
    const response = await this.$axios({
      ...vueContext.state.APIs.getSku,
      headers: {
        Authorization: Cookies.get('token'),
      },
      params: {
        ...payload,
      },
    })

    return response
  },

  // Hàm gọi API thêm mới sản phẩm du lịch
  async addTourismProduct(vueContext, payload) {
    const response = await this.$axios({
      ...vueContext.state.APIs.addTourismProduct,
      headers: {
        Authorization: Cookies.get('token'),
      },
      data: {
        ...payload,
      },
    })

    return response
  },
}

export default {
  state,
  actions,
}
