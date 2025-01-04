export interface ProductAttrVO {
  /**
   * 主键
   */
  id: string | number;

  /**
   * 商品
   */
  productId: string | number;

  /**
   * 属性名
   */
  attrName: string;

  /**
   * 属性值
   */
  attrValues: string;

  /**
   * 状态
   */
  status: string;

  /**
   * 创建时间
   */
  createTime: string;

  /**
   * 更新时间
   */
  updateTime: string;

}

export interface ProductAttrForm extends BaseEntity {
  /**
   * 主键
   */
  id?: string | number;

  /**
   * 商品
   */
  productId?: string | number;

  /**
   * 属性名
   */
  attrName?: string;

  /**
   * 属性值
   */
  attrValues?: string;

  /**
   * 状态
   */
  status?: string;

}

export interface ProductAttrQuery extends PageQuery {

  /**
   * 商品
   */
  productId?: string | number;

  /**
   * 属性名
   */
  attrName?: string;

  /**
   * 属性值
   */
  attrValues?: string;

  /**
   * 状态
   */
  status?: string;

    /**
     * 日期范围参数
     */
    params?: any;
}



