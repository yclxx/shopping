<template>
  <div class="p-2">
    <transition :enter-active-class="proxy?.animate.searchAnimate.enter"
      :leave-active-class="proxy?.animate.searchAnimate.leave">
      <div v-show="showSearch" class="mb-[10px]">
        <el-card shadow="hover">
          <el-form ref="queryFormRef" :model="queryParams" :inline="true">
            <el-form-item label="商品ID" prop="productId">
              <el-input v-model="queryParams.productId" placeholder="请输入商品ID" clearable @keyup.enter="handleQuery" />
            </el-form-item>
            <el-form-item label="商品名称" prop="productName">
              <el-input v-model="queryParams.productName" placeholder="请输入商品名称" clearable @keyup.enter="handleQuery" />
            </el-form-item>
            <el-form-item label="商品类型" prop="productType">
              <el-select v-model="queryParams.productType" placeholder="请选择商品类型" clearable>
                <el-option v-for="dict in t_product_type" :key="dict.value" :label="dict.label" :value="dict.value" />
              </el-select>
            </el-form-item>
            <el-form-item label="状态" prop="status">
              <el-select v-model="queryParams.status" placeholder="请选择状态" clearable>
                <el-option v-for="dict in sys_normal_disable" :key="dict.value" :label="dict.label"
                  :value="dict.value" />
              </el-select>
            </el-form-item>
            <el-form-item label="显示首页" prop="showIndex">
              <el-select v-model="queryParams.showIndex" placeholder="请选择显示首页" clearable>
                <el-option v-for="dict in sys_yes_no" :key="dict.value" :label="dict.label" :value="dict.value" />
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
              v-hasPermi="['shopping:product:add']">新增</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="success" plain icon="Edit" :disabled="single" @click="handleUpdate()"
              v-hasPermi="['shopping:product:edit']">修改</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" plain icon="Delete" :disabled="multiple" @click="handleDelete()"
              v-hasPermi="['shopping:product:remove']">删除</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="warning" plain icon="Download" @click="handleExport"
              v-hasPermi="['shopping:product:export']">导出</el-button>
          </el-col>
          <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
        </el-row>
      </template>

      <el-table v-loading="loading" :data="productList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column label="商品ID" align="center" prop="productId" v-if="true" />
        <el-table-column label="商品名称" align="center" prop="productName" />
        <el-table-column label="商品图片" align="center" prop="productImg" />
        <el-table-column label="商品类型" align="center" prop="productType">
          <template #default="scope">
            <dict-tag :options="t_product_type" :value="scope.row.productType" />
          </template>
        </el-table-column>
        <el-table-column label="状态" align="center" prop="status">
          <template #default="scope">
            <dict-tag :options="sys_normal_disable" :value="scope.row.status" />
          </template>
        </el-table-column>
        <el-table-column label="排序" align="center" prop="sort" />
        <el-table-column label="被搜索" align="center" prop="searchStatus">
          <template #default="scope">
            <dict-tag :options="sys_yes_no" :value="scope.row.searchStatus" />
          </template>
        </el-table-column>
        <el-table-column label="显示首页" align="center" prop="showIndex">
          <template #default="scope">
            <dict-tag :options="sys_yes_no" :value="scope.row.showIndex" />
          </template>
        </el-table-column>
        <el-table-column label="展示开始时间" align="center" prop="showStartDate" width="180">
          <template #default="scope">
            <span>{{ parseTime(scope.row.showStartDate, '{y}-{m}-{d}') }}</span>
          </template>
        </el-table-column>
        <el-table-column label="展示结束时间" align="center" prop="showEndDate" width="180">
          <template #default="scope">
            <span>{{ parseTime(scope.row.showEndDate, '{y}-{m}-{d}') }}</span>
          </template>
        </el-table-column>
        <el-table-column label="售卖开始时间" align="center" prop="sellStartDate" width="180">
          <template #default="scope">
            <span>{{ parseTime(scope.row.sellStartDate, '{y}-{m}-{d}') }}</span>
          </template>
        </el-table-column>
        <el-table-column label="售卖结束时间" align="center" prop="sellEndDate" width="180">
          <template #default="scope">
            <span>{{ parseTime(scope.row.sellEndDate, '{y}-{m}-{d}') }}</span>
          </template>
        </el-table-column>
        <el-table-column label="售价" align="center" prop="price" />
        <el-table-column label="原价" align="center" prop="otPrice" />
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
                v-hasPermi="['shopping:product:edit']"></el-button>
            </el-tooltip>
            <el-tooltip content="删除" placement="top">
              <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)"
                v-hasPermi="['shopping:product:remove']"></el-button>
            </el-tooltip>
          </template>
        </el-table-column>
      </el-table>

      <pagination v-show="total > 0" :total="total" v-model:page="queryParams.pageNum"
        v-model:limit="queryParams.pageSize" @pagination="getList" />
    </el-card>
    <!-- 添加或修改商品信息对话框 -->
    <el-dialog :title="dialog.title" v-model="dialog.visible" width="500px" append-to-body>
      <el-form ref="productFormRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="商品名称" prop="productName">
          <el-input v-model="form.productName" placeholder="请输入商品名称" />
        </el-form-item>
        <el-form-item label="商品图片" prop="productImg">
          <el-input v-model="form.productImg" placeholder="请输入商品图片" />
        </el-form-item>
        <el-form-item label="商品类型" prop="productType">
          <el-select v-model="form.productType" placeholder="请选择商品类型">
            <el-option v-for="dict in t_product_type" :key="dict.value" :label="dict.label"
              :value="dict.value"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-radio-group v-model="form.status">
            <el-radio v-for="dict in sys_normal_disable" :key="dict.value" :value="dict.value">{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="排序" prop="sort">
          <el-input v-model="form.sort" placeholder="请输入排序" />
        </el-form-item>
        <el-form-item label="被搜索" prop="searchStatus">
          <el-radio-group v-model="form.searchStatus">
            <el-radio v-for="dict in sys_yes_no" :key="dict.value" :value="dict.value">{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="显示首页" prop="showIndex">
          <el-radio-group v-model="form.showIndex">
            <el-radio v-for="dict in sys_yes_no" :key="dict.value" :value="dict.value">{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="展示开始时间" prop="showStartDate">
          <el-date-picker clearable v-model="form.showStartDate" type="datetime" value-format="YYYY-MM-DD HH:mm:ss"
            placeholder="请选择展示开始时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="展示结束时间" prop="showEndDate">
          <el-date-picker clearable v-model="form.showEndDate" type="datetime" value-format="YYYY-MM-DD HH:mm:ss"
            placeholder="请选择展示结束时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="售卖开始时间" prop="sellStartDate">
          <el-date-picker clearable v-model="form.sellStartDate" type="datetime" value-format="YYYY-MM-DD HH:mm:ss"
            placeholder="请选择售卖开始时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="售卖结束时间" prop="sellEndDate">
          <el-date-picker clearable v-model="form.sellEndDate" type="datetime" value-format="YYYY-MM-DD HH:mm:ss"
            placeholder="请选择售卖结束时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="售价" prop="price">
          <el-input v-model="form.price" placeholder="请输入售价" />
        </el-form-item>
        <el-form-item label="原价" prop="otPrice">
          <el-input v-model="form.otPrice" placeholder="请输入原价" />
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

<script setup name="Product" lang="ts">
  import { listProduct, getProduct, delProduct, addProduct, updateProduct } from '@/api/shopping/product';
  import { ProductVO, ProductQuery, ProductForm } from '@/api/shopping/product/types';

  const { proxy } = getCurrentInstance() as ComponentInternalInstance;
  const { sys_yes_no, t_product_type, sys_normal_disable } = toRefs<any>(proxy?.useDict('sys_yes_no', 't_product_type', 'sys_normal_disable'));

  const productList = ref<ProductVO[]>([]);
  const buttonLoading = ref(false);
  const loading = ref(true);
  const showSearch = ref(true);
  const ids = ref<Array<string | number>>([]);
  const single = ref(true);
  const multiple = ref(true);
  const total = ref(0);

  const queryFormRef = ref<ElFormInstance>();
  const productFormRef = ref<ElFormInstance>();

  const dialog = reactive<DialogOption>({
    visible: false,
    title: ''
  });

  const initFormData : ProductForm = {
    productId: undefined,
    productName: undefined,
    productImg: undefined,
    productType: undefined,
    status: undefined,
    sort: undefined,
    searchStatus: undefined,
    showIndex: undefined,
    showStartDate: undefined,
    showEndDate: undefined,
    sellStartDate: undefined,
    sellEndDate: undefined,
    price: undefined,
    otPrice: undefined,
  }
  const data = reactive<PageData<ProductForm, ProductQuery>>({
    form: { ...initFormData },
    queryParams: {
      pageNum: 1,
      pageSize: 10,
      productId: undefined,
      productName: undefined,
      productType: undefined,
      status: undefined,
      showIndex: undefined,
      params: {
      }
    },
    rules: {
      productId: [
        { required: true, message: "商品ID不能为空", trigger: "blur" }
      ],
      productName: [
        { required: true, message: "商品名称不能为空", trigger: "blur" }
      ],
      productImg: [
        { required: true, message: "商品图片不能为空", trigger: "blur" }
      ],
      productType: [
        { required: true, message: "商品类型不能为空", trigger: "change" }
      ],
      status: [
        { required: true, message: "状态不能为空", trigger: "change" }
      ],
      showEndDate: [
        { required: true, message: "展示结束时间不能为空", trigger: "blur" }
      ],
      sellEndDate: [
        { required: true, message: "售卖结束时间不能为空", trigger: "blur" }
      ],
      price: [
        { required: true, message: "售价不能为空", trigger: "blur" }
      ],
    }
  });

  const { queryParams, form, rules } = toRefs(data);

  /** 查询商品信息列表 */
  const getList = async () => {
    loading.value = true;
    const res = await listProduct(queryParams.value);
    productList.value = res.rows;
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
    productFormRef.value?.resetFields();
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
  const handleSelectionChange = (selection : ProductVO[]) => {
    ids.value = selection.map(item => item.productId);
    single.value = selection.length != 1;
    multiple.value = !selection.length;
  }

  /** 新增按钮操作 */
  const handleAdd = () => {
    reset();
    dialog.visible = true;
    dialog.title = "添加商品信息";
  }

  /** 修改按钮操作 */
  const handleUpdate = async (row ?: ProductVO) => {
    reset();
    const _productId = row?.productId || ids.value[0]
    const res = await getProduct(_productId);
    Object.assign(form.value, res.data);
    dialog.visible = true;
    dialog.title = "修改商品信息";
  }

  /** 提交按钮 */
  const submitForm = () => {
    productFormRef.value?.validate(async (valid : boolean) => {
      if (valid) {
        buttonLoading.value = true;
        if (form.value.productId) {
          await updateProduct(form.value).finally(() => buttonLoading.value = false);
        } else {
          await addProduct(form.value).finally(() => buttonLoading.value = false);
        }
        proxy?.$modal.msgSuccess("操作成功");
        dialog.visible = false;
        await getList();
      }
    });
  }

  /** 删除按钮操作 */
  const handleDelete = async (row ?: ProductVO) => {
    const _productIds = row?.productId || ids.value;
    await proxy?.$modal.confirm('是否确认删除商品信息编号为"' + _productIds + '"的数据项？').finally(() => loading.value = false);
    await delProduct(_productIds);
    proxy?.$modal.msgSuccess("删除成功");
    await getList();
  }

  /** 导出按钮操作 */
  const handleExport = () => {
    proxy?.download('admin/product/export', {
      ...queryParams.value
    }, `product_${new Date().getTime()}.xlsx`)
  }

  onMounted(() => {
    getList();
  });
</script>
