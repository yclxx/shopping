<template>
  <div class="p-2">
    <transition :enter-active-class="proxy?.animate.searchAnimate.enter"
      :leave-active-class="proxy?.animate.searchAnimate.leave">
      <div v-show="showSearch" class="mb-[10px]">
        <el-card shadow="hover">
          <el-form ref="queryFormRef" :model="queryParams" :inline="true">
            <el-form-item label="商品属性索引值 (attr_value|attr_value[|....])" prop="sku">
              <el-input v-model="queryParams.sku" placeholder="请输入商品属性索引值 (attr_value|attr_value[|....])" clearable
                @keyup.enter="handleQuery" />
            </el-form-item>
            <el-form-item label="attr_values 创建更新时的属性对应" prop="attrValue">
              <el-input v-model="queryParams.attrValue" placeholder="请输入attr_values 创建更新时的属性对应" clearable
                @keyup.enter="handleQuery" />
            </el-form-item>
            <el-form-item label="状态" prop="status">
              <el-select v-model="queryParams.status" placeholder="请选择状态" clearable>
                <el-option v-for="dict in sys_normal_disable" :key="dict.value" :label="dict.label"
                  :value="dict.value" />
              </el-select>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
              <el-button icon="Refresh" @click="resetQuery">重置</el-button>
            </el-form-item>
          </el-form>
        </el-card>
      </div>
    </transition>

    <el-card shadow="never">
      <template #header>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" plain icon="Plus" @click="handleAdd"
              v-hasPermi="['shopping:productAttrValue:add']">新增</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="success" plain icon="Edit" :disabled="single" @click="handleUpdate()"
              v-hasPermi="['shopping:productAttrValue:edit']">修改</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" plain icon="Delete" :disabled="multiple" @click="handleDelete()"
              v-hasPermi="['shopping:productAttrValue:remove']">删除</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="warning" plain icon="Download" @click="handleExport"
              v-hasPermi="['shopping:productAttrValue:export']">导出</el-button>
          </el-col>
          <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
        </el-row>
      </template>

      <el-table v-loading="loading" :data="productAttrValueList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column label="主键" align="center" prop="id" v-if="true" />
        <el-table-column label="商品" align="center" prop="productId" />
        <el-table-column label="商品属性索引值 (attr_value|attr_value[|....])" align="center" prop="sku" />
        <el-table-column label="图片" align="center" prop="imgUrl" width="100">
          <template #default="scope">
            <image-preview :src="scope.row.imgUrl" :width="50" :height="50" />
          </template>
        </el-table-column>
        <el-table-column label="售价" align="center" prop="price" />
        <el-table-column label="成本价" align="center" prop="cost" />
        <el-table-column label="原价" align="center" prop="otPrice" />
        <el-table-column label="总库存" align="center" prop="totalStock" />
        <el-table-column label="销量" align="center" prop="sales" />
        <el-table-column label="剩余库存" align="center" prop="stock" />
        <el-table-column label="attr_values 创建更新时的属性对应" align="center" prop="attrValue" />
        <el-table-column label="状态" align="center" prop="status">
          <template #default="scope">
            <dict-tag :options="sys_normal_disable" :value="scope.row.status" />
          </template>
        </el-table-column>
        <el-table-column label="创建时间" align="center" prop="createTime" width="180">
          <template #default="scope">
            <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
          </template>
        </el-table-column>
        <el-table-column label="更新时间" align="center" prop="updateTime" width="180">
          <template #default="scope">
            <span>{{ parseTime(scope.row.updateTime, '{y}-{m}-{d}') }}</span>
          </template>
        </el-table-column>
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
          <template #default="scope">
            <el-tooltip content="修改" placement="top">
              <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)"
                v-hasPermi="['shopping:productAttrValue:edit']"></el-button>
            </el-tooltip>
            <el-tooltip content="删除" placement="top">
              <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)"
                v-hasPermi="['shopping:productAttrValue:remove']"></el-button>
            </el-tooltip>
          </template>
        </el-table-column>
      </el-table>

      <pagination v-show="total > 0" :total="total" v-model:page="queryParams.pageNum"
        v-model:limit="queryParams.pageSize" @pagination="getList" />
    </el-card>
    <!-- 添加或修改商品属性值对话框 -->
    <el-dialog :title="dialog.title" v-model="dialog.visible" width="500px" append-to-body>
      <el-form ref="productAttrValueFormRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="商品属性索引值 (attr_value|attr_value[|....])" prop="sku">
          <el-input v-model="form.sku" placeholder="请输入商品属性索引值 (attr_value|attr_value[|....])" />
        </el-form-item>
        <el-form-item label="图片" prop="img">
          <image-upload v-model="form.img" />
        </el-form-item>
        <el-form-item label="售价" prop="price">
          <el-input v-model="form.price" placeholder="请输入售价" />
        </el-form-item>
        <el-form-item label="成本价" prop="cost">
          <el-input v-model="form.cost" placeholder="请输入成本价" />
        </el-form-item>
        <el-form-item label="原价" prop="otPrice">
          <el-input v-model="form.otPrice" placeholder="请输入原价" />
        </el-form-item>
        <el-form-item label="总库存" prop="totalStock">
          <el-input v-model="form.totalStock" placeholder="请输入总库存" />
        </el-form-item>
        <el-form-item label="销量" prop="sales">
          <el-input v-model="form.sales" placeholder="请输入销量" />
        </el-form-item>
        <el-form-item label="剩余库存" prop="stock">
          <el-input v-model="form.stock" placeholder="请输入剩余库存" />
        </el-form-item>
        <el-form-item label="attr_values 创建更新时的属性对应" prop="attrValue">
          <el-input v-model="form.attrValue" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-radio-group v-model="form.status">
            <el-radio v-for="dict in sys_normal_disable" :key="dict.value" :value="dict.value">{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button :loading="buttonLoading" type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup name="ProductAttrValue" lang="ts">
  import { listProductAttrValue, getProductAttrValue, delProductAttrValue, addProductAttrValue, updateProductAttrValue } from '@/api/shopping/productAttrValue';
  import { ProductAttrValueVO, ProductAttrValueQuery, ProductAttrValueForm } from '@/api/shopping/productAttrValue/types';

  const { proxy } = getCurrentInstance() as ComponentInternalInstance;
  const { sys_normal_disable } = toRefs<any>(proxy?.useDict('sys_normal_disable'));

  const productAttrValueList = ref<ProductAttrValueVO[]>([]);
  const buttonLoading = ref(false);
  const loading = ref(true);
  const showSearch = ref(true);
  const ids = ref<Array<string | number>>([]);
  const single = ref(true);
  const multiple = ref(true);
  const total = ref(0);

  const queryFormRef = ref<ElFormInstance>();
  const productAttrValueFormRef = ref<ElFormInstance>();

  const dialog = reactive<DialogOption>({
    visible: false,
    title: ''
  });

  const initFormData : ProductAttrValueForm = {
    id: undefined,
    productId: undefined,
    sku: undefined,
    img: undefined,
    price: undefined,
    cost: undefined,
    otPrice: undefined,
    totalStock: undefined,
    sales: undefined,
    stock: undefined,
    attrValue: undefined,
    status: undefined,
  }
  const data = reactive<PageData<ProductAttrValueForm, ProductAttrValueQuery>>({
    form: { ...initFormData },
    queryParams: {
      pageNum: 1,
      pageSize: 10,
      productId: undefined,
      sku: undefined,
      attrValue: undefined,
      status: undefined,
      params: {
      }
    },
    rules: {
      id: [
        { required: true, message: "主键不能为空", trigger: "blur" }
      ],
      productId: [
        { required: true, message: "商品不能为空", trigger: "change" }
      ],
      sku: [
        { required: true, message: "商品属性索引值 (attr_value|attr_value[|....])不能为空", trigger: "blur" }
      ],
      img: [
        { required: true, message: "图片不能为空", trigger: "blur" }
      ],
      price: [
        { required: true, message: "售价不能为空", trigger: "blur" }
      ],
      cost: [
        { required: true, message: "成本价不能为空", trigger: "blur" }
      ],
      totalStock: [
        { required: true, message: "总库存不能为空", trigger: "blur" }
      ],
      attrValue: [
        { required: true, message: "attr_values 创建更新时的属性对应不能为空", trigger: "blur" }
      ],
      status: [
        { required: true, message: "状态不能为空", trigger: "change" }
      ],
    }
  });

  const { queryParams, form, rules } = toRefs(data);

  /** 查询商品属性值列表 */
  const getList = async () => {
    loading.value = true;
    const res = await listProductAttrValue(queryParams.value);
    productAttrValueList.value = res.rows;
    total.value = res.total;
    loading.value = false;
  }

  /** 取消按钮 */
  const cancel = () => {
    reset();
    dialog.visible = false;
  }

  /** 表单重置 */
  const reset = () => {
    form.value = { ...initFormData };
    productAttrValueFormRef.value?.resetFields();
  }

  /** 搜索按钮操作 */
  const handleQuery = () => {
    queryParams.value.pageNum = 1;
    getList();
  }

  /** 重置按钮操作 */
  const resetQuery = () => {
    queryFormRef.value?.resetFields();
    handleQuery();
  }

  /** 多选框选中数据 */
  const handleSelectionChange = (selection : ProductAttrValueVO[]) => {
    ids.value = selection.map(item => item.id);
    single.value = selection.length != 1;
    multiple.value = !selection.length;
  }

  /** 新增按钮操作 */
  const handleAdd = () => {
    reset();
    dialog.visible = true;
    dialog.title = "添加商品属性值";
  }

  /** 修改按钮操作 */
  const handleUpdate = async (row ?: ProductAttrValueVO) => {
    reset();
    const _id = row?.id || ids.value[0]
    const res = await getProductAttrValue(_id);
    Object.assign(form.value, res.data);
    dialog.visible = true;
    dialog.title = "修改商品属性值";
  }

  /** 提交按钮 */
  const submitForm = () => {
    productAttrValueFormRef.value?.validate(async (valid : boolean) => {
      if (valid) {
        buttonLoading.value = true;
        if (form.value.id) {
          await updateProductAttrValue(form.value).finally(() => buttonLoading.value = false);
        } else {
          await addProductAttrValue(form.value).finally(() => buttonLoading.value = false);
        }
        proxy?.$modal.msgSuccess("操作成功");
        dialog.visible = false;
        await getList();
      }
    });
  }

  /** 删除按钮操作 */
  const handleDelete = async (row ?: ProductAttrValueVO) => {
    const _ids = row?.id || ids.value;
    await proxy?.$modal.confirm('是否确认删除商品属性值编号为"' + _ids + '"的数据项？').finally(() => loading.value = false);
    await delProductAttrValue(_ids);
    proxy?.$modal.msgSuccess("删除成功");
    await getList();
  }

  /** 导出按钮操作 */
  const handleExport = () => {
    proxy?.download('admin/productAttrValue/export', {
      ...queryParams.value
    }, `productAttrValue_${new Date().getTime()}.xlsx`)
  }

  onMounted(() => {
    getList();
  });
</script>
