import ajax from './ajax'

export const reqCoupon = (businessId) => ajax('/api/coupon/reqCoupon', { businessId })

export const insertCoupon = (couponMess) => ajax('/api/coupon/insertCoupon', { couponMess }, ' POST ')

export const deleteCoupon = (couponId) => ajax('/api/coupon/deleteCoupon', { couponId })
