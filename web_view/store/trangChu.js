import APIs from '~/assets/configurations/API_Config'
import uniqid from 'uniqid'
import Cookies from 'js-cookie'

export const state = () => ({
  ListBaner: {
    url: '/shopnow/home/banner',
    method: 'GET'
  },
  newsFeed: {
    url: '/shopnow/home/newsFeed',
    method: 'GET'
  },
  home: {
    url: '/shopnow/home/productCategory',
    method: 'GET'
  },
  searchTrending: {
    url: '/shopnow/home/searchTrending',
    method: 'GET'
  },
  sellingProduct: {
    url: '/shopnow/home/sellingProduct',
    method: 'GET'
  },
  flashSale: {
    url: '/shopnow/home/flashSale',
    method: 'GET'
  },
  suggestionToday: {
    url: '/shopnow/home/suggestionToday',
    method: 'GET'
  }
})
export const actions = {
  async suggestionToday(vueContext, payload) {
    const transid = uniqid()
    const response = await this.$axios({
      url: vueContext.state.suggestionToday.url,
      method: vueContext.state.suggestionToday.method,
      params: {
        channel: APIs.channel,
        transid,
        ...payload
      }
    })
    return response
  },
  async flashSale(vueContext, payload) {
    const transid = uniqid()
    const response = await this.$axios({
      url: vueContext.state.flashSale.url,
      method: vueContext.state.flashSale.method,
      params: {
        channel: APIs.channel,
        transid,
        ...payload
      }
    })
    return response
  },
  async searchTrending(vueContext, payload) {
    const transid = uniqid()
    const response = await this.$axios({
      url: vueContext.state.searchTrending.url,
      method: vueContext.state.searchTrending.method,
      params: {
        channel: APIs.channel,
        transid,
        ...payload
      }
    })
    return response
  },
  async sellingProduct(vueContext, payload) {
    const transid = uniqid()
    const response = await this.$axios({
      url: vueContext.state.sellingProduct.url,
      method: vueContext.state.sellingProduct.method,
      params: {
        channel: APIs.channel,
        transid,
        ...payload
      }
    })
    return response
  },
  async home(vueContext, payload) {
    const transid = uniqid()
    const response = await this.$axios({
      url: vueContext.state.home.url,
      method: vueContext.state.home.method,
      params: {
        channel: APIs.channel,
        transid,
        ...payload
      }
    })
    return response
  },
  async ListBaner(vueContext, payload) {
    const transid = uniqid()
    const response = await this.$axios({
      url: vueContext.state.ListBaner.url,
      method: vueContext.state.ListBaner.method,
      params: {
        channel: APIs.channel,
        transid,
        ...payload
      }
    })
    return response
  },
  async newsFeed(vueContext, payload) {
    const transid = uniqid()
    const response = await this.$axios({
      url: vueContext.state.newsFeed.url,
      method: vueContext.state.newsFeed.method,
      params: {
        channel: APIs.channel,
        transid,
        ...payload
      }
    })
    return response
  }
}
