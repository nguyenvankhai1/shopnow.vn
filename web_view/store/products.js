import APIs from '~/assets/configurations/API_Config'
import uniqid from 'uniqid'
import Cookies from 'js-cookie'

export const state = () => ({
  getProduct: {
    url: '/shopnow/enduser/product',
    method: 'GET'
  },
  accuracyOTP: {
    url: '/shopnow/enduser/customer/verify',
    method: 'POST'
  }
})
export const actions = {
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
