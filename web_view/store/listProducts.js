import uniqid from 'uniqid'
import APIs from '~/assets/configurations/API_Config'
import Cookies from 'js-cookie'

export const state = () => ({
  attribute: {
    url: 'shopnow/enduser/product',
    method: 'GET'
  },
  onTop: {
    url: 'shopnow/productCategory/onTop',
    method: 'GET'
  }
})
export const actions = {
  async onTop(vueContext, payload) {
    const transid = uniqid()
    const response = await this.$axios({
      url: vueContext.state.onTop.url,
      method: vueContext.state.onTop.method,
      headers: {
        Authorization: Cookies.get('token')
      },
      params: {
        channel: APIs.channel,
        transid,
        ...payload
      }
    })
    return response
  },
  async get_list_Products(vueContext, payload) {
    const transid = uniqid()
    const response = await this.$axios({
      url: vueContext.state.list.url,
      method: vueContext.state.list.method,
      headers: {
        Authorization: Cookies.get('token')
      },
      params: {
        channel: APIs.channel,
        transid,
        ...payload
      }
    })
    return response
  }
}
