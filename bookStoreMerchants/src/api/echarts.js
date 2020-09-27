import ajax from './ajax'

export const reqShowData = () => ajax('/api/charts/reqShowData')

export const reqPieData = () => ajax('/api/charts/reqPieData')

export const reqSaleMoney = () => ajax('/api/charts/reqSaleMoney')
