export interface ProductVO {
  /**
   * 商品ID
   */
  productId : string | number;

  /**
   * 商品名称
   */
  productName : string;

  /**
   * 商品图片
   */
  productImg : string | number;

  /**
   * 商品图片Url
   */
  productImgUrl : string;

  /**
   * 商品类型
   */
  productType : string;

  /**
   * 状态
   */
  status : string;

  /**
   * 排序
   */
  sort : number;

  /**
   * 被搜索
   */
  searchStatus : string;

  /**
   * 显示首页
   */
  showIndex : string;

  /**
   * 展示开始时间
   */
  showStartDate : string;

  /**
   * 展示结束时间
   */
  showEndDate : string;

  /**
   * 售卖开始时间
   */
  sellStartDate : string;

  /**
   * 售卖结束时间
   */
  sellEndDate : string;

  /**
   * 售价
   */
  price : number;

  /**
   * 原价
   */
  otPrice : number;

  /**
   * 创建时间
   */
  createTime : string;

  /**
   * 更新时间
   */
  updateTime : string;

}

export interface ProductForm extends BaseEntity {
  /**
   * 商品ID
   */
  productId ?: string | number;

  /**
   * 商品名称
   */
  productName ?: string;

  /**
   * 商品图片
   */
  productImg ?: string | number;

  /**
   * 商品类型
   */
  productType ?: string;

  /**
   * 状态
   */
  status ?: string;

  /**
   * 排序
   */
  sort ?: number;

  /**
   * 被搜索
   */
  searchStatus ?: string;

  /**
   * 显示首页
   */
  showIndex ?: string;

  /**
   * 展示开始时间
   */
  showStartDate ?: string;

  /**
   * 展示结束时间
   */
  showEndDate ?: string;

  /**
   * 售卖开始时间
   */
  sellStartDate ?: string;

  /**
   * 售卖结束时间
   */
  sellEndDate ?: string;

  /**
   * 售价
   */
  price ?: number;

  /**
   * 原价
   */
  otPrice ?: number;

}

export interface ProductQuery extends PageQuery {

  /**
   * 商品ID
   */
  productId ?: string | number;

  /**
   * 商品名称
   */
  productName ?: string;

  /**
   * 商品类型
   */
  productType ?: string;

  /**
   * 状态
   */
  status ?: string;

  /**
   * 显示首页
   */
  showIndex ?: string;

  /**
   * 日期范围参数
   */
  params ?: any;
}
