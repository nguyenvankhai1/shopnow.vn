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
  },
  search: {
    url: '/shopnow/home/search',
    method: 'GET'
  },
  apiTinh: {
    url: '/utility/location/province',
    method: 'GET'
  },
  apiHuyen: {
    url: '/utility/location/district?provinceId',
    method: 'GET'
  },
  apiKhuvuc: {
    url: '/utility/location/ward?districtId',
    method: 'GET'
  },
  tinhPhi: {
    url: '/shipping/fee',
    method: 'POST'
  }
})
export const actions = {
  async order(vueContext, payload) {
    const transid = uniqid()
    const response = await this.$axios({
      url: 'http://192.168.0.108:8083/utility/shipping/fee',
      method: 'POST',
      data: {
        channel: APIs.channel,
        transid,
        ...payload
      }
    })
    return response
  },
  async tinhPhi(vueContext, payload) {
    const transid = uniqid()
    const response = await this.$axios({
      url: 'http://192.168.0.108:8083/utility/shipping/fee',
      method: vueContext.state.tinhPhi.method,
      data: {
        channel: APIs.channel,
        transid,
        ...payload
      }
    })
    return response
  },
  async apiKhuvuc(vueContext, payload) {
    const transid = uniqid()
    const response = await this.$axios({
      url: 'http://192.168.0.108:8083/utility/location/ward',
      method: vueContext.state.apiKhuvuc.method,
      params: {
        channel: APIs.channel,
        transid,
        ...payload
      }
    })
    return response
  },
  async apiHuyen(vueContext, payload) {
    const transid = uniqid()
    const response = await this.$axios({
      url: 'http://192.168.0.108:8083/utility/location/district',
      method: vueContext.state.apiHuyen.method,
      params: {
        channel: APIs.channel,
        transid,
        ...payload
      }
    })
    return response
  },
  async apiTinh(vueContext, payload) {
    const transid = uniqid()
    const response = await this.$axios({
      url: 'http://192.168.0.108:8083/utility/location/province',
      method: vueContext.state.apiTinh.method,
      params: {
        channel: APIs.channel,
        transid,
        ...payload
      }
    })
    return response
  },
  async search(vueContext, payload) {
    const transid = uniqid()
    const response = await this.$axios({
      url: vueContext.state.search.url,
      method: vueContext.state.search.method,
      params: {
        channel: APIs.channel,
        transid,
        ...payload
      }
    })
    return response
  },
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
