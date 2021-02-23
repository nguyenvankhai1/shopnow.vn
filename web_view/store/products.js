import APIs from '~/assets/configurations/API_Config'
import uniqid from 'uniqid'
import Cookies from 'js-cookie'

export const state = () => ({
  detailProduct: {
    url: '/shopnow/product/detail',
    method: 'GET'
  },
  getProduct: {
    url: '/shopnow/product',
    method: 'GET'
  },
  productCategory: {
    url: '/shopnow/cms/productCategory',
    method: 'GET'
  },
  accuracyOTP: {
    url: '/shopnow/customer/verify',
    method: 'POST'
  }
})
export const actions = {
  async productCategory(vueContext, payload) {
    const transid = uniqid()
    const response = await this.$axios({
      url: vueContext.state.productCategory.url,
      method: vueContext.state.productCategory.method,
      params: {
        channel: APIs.channel,
        transid,
        ...payload
      }
    })
    return response
  },

  async detailProduct(vueContext, payload) {
    const transid = uniqid()
    const response = await this.$axios({
      url: vueContext.state.detailProduct.url,
      method: vueContext.state.detailProduct.method,
      params: {
        ...payload
        // channel: APIs.channel,
        // transid
      }
    })
    return response
  },
  async getProduct(vueContext, payload) {
    const transid = uniqid()
    const response = await this.$axios({
      url: vueContext.state.getProduct.url,
      method: vueContext.state.getProduct.method,
      params: {
        channel: APIs.channel,
        transid,
        ...payload
      }
    })
    return response
  },
  async accuracyOTP(vueContext, payload) {
    const transid = uniqid()
    const response = await this.$axios({
      url: vueContext.state.accuracyOTP.url,
      method: vueContext.state.accuracyOTP.method,
      data: {
        channel: APIs.channel,
        transid,
        ...payload
      }
    })
    return response
  }
}
