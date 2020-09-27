import ajax from './ajax'

// 修改个人信息,写在comment控制器下
export const changeMessage = ({ businessId, phone, name }) => ajax('/api/business/changeMessage', { businessId, phone, name }, 'POST')

export const changePassword = ({ businessId, newPass }) => ajax('/api/business/changePassword', { businessId, newPass }, 'POST')
