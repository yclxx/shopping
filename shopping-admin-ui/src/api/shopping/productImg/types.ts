export interface ProductImgVO {
  /**
   * ID
   */
  id : string | number;

  /**
   * 商品
   */
  productId : string | number;

  /**
   * 图片
   */
  img : string | number;

  /**
   * 图片Url
   */
  imgUrl : string;
  /**
   * 图片归属
   */
  imgAttribution : string;

  /**
   * 状态
   */
  status : string;

  /**
   * 排序
   */
  sort : number;

  /**
   * 创建时间
   */
  createTime : string;

  /**
   * 更新时间
   */
  updateTime : string;

}

export interface ProductImgForm extends BaseEntity {
  /**
   * ID
   */
  id ?: string | number;

  /**
   * 商品
   */
  productId ?: string | number;

  /**
   * 图片
   */
  img ?: string | number;

  /**
   * 图片归属
   */
  imgAttribution ?: string;

  /**
   * 状态
   */
  status ?: string;

  /**
   * 排序
   */
  sort ?: number;

}

export interface ProductImgQuery extends PageQuery {

  /**
   * 商品
   */
  productId ?: string | number;

  /**
   * 图片归属
   */
  imgAttribution ?: string;

  /**
   * 状态
   */
  status ?: string;

  /**
   * 日期范围参数
   */
  params ?: any;
}
