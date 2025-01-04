export interface ProductAttrValueVO {
  /**
   * 主键
   */
  id: string | number;

  /**
   * 商品
   */
  productId: string | number;

  /**
   * 商品属性索引值 (attr_value|attr_value[|....])
   */
  sku: string;

  /**
   * 图片
   */
  img: string;

  /**
   * 图片Url
   */
  imgUrl: string;
  /**
   * 售价
   */
  price: number;

  /**
   * 成本价
   */
  cost: number;

  /**
   * 原价
   */
  otPrice: number;

  /**
   * 总库存
   */
  totalStock: number;

  /**
   * 销量
   */
  sales: number;

  /**
   * 剩余库存
   */
  stock: number;

  /**
   * attr_values 创建更新时的属性对应
   */
  attrValue: string;

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

export interface ProductAttrValueForm extends BaseEntity {
  /**
   * 主键
   */
  id?: string | number;

  /**
   * 商品
   */
  productId?: string | number;

  /**
   * 商品属性索引值 (attr_value|attr_value[|....])
   */
  sku?: string;

  /**
   * 图片
   */
  img?: string;

  /**
   * 售价
   */
  price?: number;

  /**
   * 成本价
   */
  cost?: number;

  /**
   * 原价
   */
  otPrice?: number;

  /**
   * 总库存
   */
  totalStock?: number;

  /**
   * 销量
   */
  sales?: number;

  /**
   * 剩余库存
   */
  stock?: number;

  /**
   * attr_values 创建更新时的属性对应
   */
  attrValue?: string;

  /**
   * 状态
   */
  status?: string;

}

export interface ProductAttrValueQuery extends PageQuery {

  /**
   * 商品
   */
  productId?: string | number;

  /**
   * 商品属性索引值 (attr_value|attr_value[|....])
   */
  sku?: string;

  /**
   * attr_values 创建更新时的属性对应
   */
  attrValue?: string;

  /**
   * 状态
   */
  status?: string;

    /**
     * 日期范围参数
     */
    params?: any;
}



