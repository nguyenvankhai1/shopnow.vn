import Cookies from 'js-cookie'
import APIs from '~/assets/configurations/API_Config'
import uniqid from 'uniqid'
export const state = () => ({
  registerAccount: {
    url: '/shopnow/enduser/customer/signup',
    method: 'POST'
  },
  username: {
    errors: [],
    value: ''
  },
  password: {
    errors: [],
    value: ''
  }
})

export const mutations = {
  setUsername(state, payload) {
    state.username = {
      ...state.username,
      value: payload,
      errors: []
    }
  },
  unsetItem(state) {
    state.username.value = ''
    state.password.value = ''
  },
  setPassword(state, payload) {
    state.password = {
      ...state.password,
      value: payload,
      errors: []
    }
  },
  usernameHasErrors(state, payload) {
    state.username = {
      ...state.username,
      errors: [payload]
    }
  },
  passwordHasErrors(state, payload) {
    state.password = {
      ...state.password,
      errors: [payload]
    }
  },
  clearErrors(state, payload) {
    state.username = {
      ...state.username,
      errors: []
    }
    state.password = {
      ...state.password,
      errors: []
    }
  }
}

export const actions = {
  async registerAccount(vueContext, payload) {
    const transid = uniqid()
    const response = await this.$axios({
      url: vueContext.state.registerAccount.url,
      method: vueContext.state.registerAccount.method,
      data: {
        channel: APIs.channel,
        transid,
        ...payload
      }
    })
    return response
  },
  isAuthenticated(vueContext, req) {
    if (req) {
      let token
      try {
        token = req.headers.cookie
          .split(';')
          .find(c => c.trim().startsWith('token='))
          .split('=')[1]
      } catch (e) {
        token = ''
      }
      return token
    } else {
      const token =
        Cookies.get('token') !== undefined ? Cookies.get('token') : ''
      return token
    }
  },

  setUsername(vueContext, payload) {
    vueContext.commit('setUsername', payload)
  },

  setPassword(vueContext, payload) {
    vueContext.commit('setPassword', payload)
  },
  // async getAccessPages(vueContext, group_id) {
  //   let res_group_info = await this.$getGroupUser(group_id)
  //   let group_error_code = res_group_info.data.error.code
  //   if (group_error_code === APIs.responses.OK.code) {
  //     //chua tat cac trang o day
  //     let data_group = res_group_info.data.data.group_page_infos
  //     let list_page = []
  //     let count = 0
  //     data_group.forEach(p => {
  //       count += 1
  //       if (1) {
  //         let temp = list_page.find(i => i.page_id === p.page_id)
  //         if (temp == undefined) {
  //           //ko tim thay page thi moi them vao
  //           list_page.push({
  //             ...p
  //           })
  //         } else {
  //           let index = list_page.indexOf(temp)
  //           list_page[index].role_id = temp.role_id + ',' + p.role_id
  //         }
  //       } else {
  //         //console.log(p)
  //       }
  //     })
  //     vueContext.dispatch('app/GetRole')
  //     //luu cac page nay +role kem voi vao session page
  //     //page se co url va role cua tung page do

  //     // Cookies.set('list_page', list_page)
  //     //localStorage.setItem('list_page', JSON.stringify(list_page))
  //     // console.log(Cookies.get('list_page'), list_page, count, 'GET COOKIE')
  //   }
  // },
  Submit(vueContext, payload) {
    const userName = vueContext.state.username.value
    const password = vueContext.state.password.value
    let ip = payload
    if (userName.indexOf(' ') >= 0) {
      vueContext.commit('usernameHasErrors', 'Tên người dùng không hợp lệ.')
      this.app.wait.end('logging')
      return
    }
    vueContext.commit('clearErrors')
    // Start calling API
    this.$login({
      userName,
      password,
      ip
    })
      .then(response => {
        let errorCode = response.data.error.code
        let data = response.data.data
        if (errorCode === Number(APIs.responses.OK.code)) {
          // Succeed
          // console.log(data, 'data')
          this.$router.push('/')
          Cookies.set('token', data.jwt)
          Cookies.set('username', data.username)
          Cookies.set('avatar', data.avatar)
        } else if (
          errorCode === APIs.login.responses.CREDENTIALS_INVALID.code
        ) {
          // Wrong credentials
          vueContext.commit(
            'usernameHasErrors',
            'Tên người dùng không hợp lệ hoặc không tồn tại'
          )
          vueContext.commit(
            'passwordHasErrors',
            'Tên người dùng không hợp lệ hoặc không tồn tại'
          )
        } else {
          // Other errors
          console.log('Xảy ra lỗi khi đăng nhập: ', response)
          this.$router.app.$notify({
            group: 'login',
            type: 'warn',
            text: APIs.responses[errorCode].message
          })
        }
        this.app.wait.end('logging')
      })
      .catch(errors => {
        this.app.wait.end('logging')
        let response = errors.response
        let request = errors.request
      })
  }
}

export const getters = {
  // getUsername(state) {
  //   return state.username
  // },
  // getPassword(state) {
  //   return state.password
  // }
}
