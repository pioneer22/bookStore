// 函数返回值promise对象
import ajax from './ajax'

// 商家请求登陆
export const reqLogin = (businessId, businessPass) => ajax('/api/business/login', { businessId, businessPass }, 'POST')

