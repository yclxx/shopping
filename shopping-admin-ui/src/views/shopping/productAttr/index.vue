<template>
  <div class="p-2">
    <transition :enter-active-class="proxy?.animate.searchAnimate.enter"
      :leave-active-class="proxy?.animate.searchAnimate.leave">
      <div v-show="showSearch" class="mb-[10px]">
        <el-card shadow="hover">
          <el-form ref="queryFormRef" :model="queryParams" :inline="true">
            <el-form-item label="属性名" prop="attrName">
              <el-input v-model="queryParams.attrName" placeholder="请输入属性名" clearable @keyup.enter="handleQuery" />
            </el-form-item>
            <el-form-item label="属性值" prop="attrValues">
              <el-input v-model="queryParams.attrValues" placeholder="请输入属性值" clearable @keyup.enter="handleQuery" />
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
              v-hasPermi="['shopping:productAttr:add']">新增</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="success" plain icon="Edit" :disabled="single" @click="handleUpdate()"
              v-hasPermi="['shopping:productAttr:edit']">修改</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" plain icon="Delete" :disabled="multiple" @click="handleDelete()"
              v-hasPermi="['shopping:productAttr:remove']">删除</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="warning" plain icon="Download" @click="handleExport"
              v-hasPermi="['shopping:productAttr:export']">导出</el-button>
          </el-col>
          <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
        </el-row>
      </template>

      <el-table v-loading="loading" :data="productAttrList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column label="主键" align="center" prop="id" v-if="true" />
        <el-table-column label="商品" align="center" prop="productId" />
        <el-table-column label="属性名" align="center" prop="attrName" />
        <el-table-column label="属性值" align="center" prop="attrValues" />
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
                v-hasPermi="['shopping:productAttr:edit']"></el-button>
            </el-tooltip>
            <el-tooltip content="删除" placement="top">
              <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)"
                v-hasPermi="['shopping:productAttr:remove']"></el-button>
            </el-tooltip>
          </template>
        </el-table-column>
      </el-table>

      <pagination v-show="total > 0" :total="total" v-model:page="queryParams.pageNum"
        v-model:limit="queryParams.pageSize" @pagination="getList" />
    </el-card>
    <!-- 添加或修改商品属性对话框 -->
    <el-dialog :title="dialog.title" v-model="dialog.visible" width="500px" append-to-body>
      <el-form ref="productAttrFormRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="属性名" prop="attrName">
          <el-input v-model="form.attrName" placeholder="请输入属性名" />
        </el-form-item>
        <el-form-item label="属性值" prop="attrValues">
          <el-input v-model="form.attrValues" placeholder="请输入属性值" />
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

<script setup name="ProductAttr" lang="ts">
  import { listProductAttr, getProductAttr, delProductAttr, addProductAttr, updateProductAttr } from '@/api/shopping/productAttr';
  import { ProductAttrVO, ProductAttrQuery, ProductAttrForm } from '@/api/shopping/productAttr/types';

  const { proxy } = getCurrentInstance() as ComponentInternalInstance;
  const { sys_normal_disable } = toRefs<any>(proxy?.useDict('sys_normal_disable'));

  const productAttrList = ref<ProductAttrVO[]>([]);
  const buttonLoading = ref(false);
  const loading = ref(true);
  const showSearch = ref(true);
  const ids = ref<Array<string | number>>([]);
  const single = ref(true);
  const multiple = ref(true);
  const total = ref(0);

  const queryFormRef = ref<ElFormInstance>();
  const productAttrFormRef = ref<ElFormInstance>();

  const dialog = reactive<DialogOption>({
    visible: false,
    title: ''
  });

  const initFormData : ProductAttrForm = {
    id: undefined,
    productId: undefined,
    attrName: undefined,
    attrValues: undefined,
    status: undefined,
  }
  const data = reactive<PageData<ProductAttrForm, ProductAttrQuery>>({
    form: { ...initFormData },
    queryParams: {
      pageNum: 1,
      pageSize: 10,
      productId: undefined,
      attrName: undefined,
      attrValues: undefined,
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
      attrName: [
        { required: true, message: "属性名不能为空", trigger: "blur" }
      ],
      attrValues: [
        { required: true, message: "属性值不能为空", trigger: "blur" }
      ],
      status: [
        { required: true, message: "状态不能为空", trigger: "change" }
      ],
    }
  });

  const { queryParams, form, rules } = toRefs(data);

  /** 查询商品属性列表 */
  const getList = async () => {
    loading.value = true;
    const res = await listProductAttr(queryParams.value);
    productAttrList.value = res.rows;
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
    productAttrFormRef.value?.resetFields();
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
  const handleSelectionChange = (selection : ProductAttrVO[]) => {
    ids.value = selection.map(item => item.id);
    single.value = selection.length != 1;
    multiple.value = !selection.length;
  }

  /** 新增按钮操作 */
  const handleAdd = () => {
    reset();
    dialog.visible = true;
    dialog.title = "添加商品属性";
  }

  /** 修改按钮操作 */
  const handleUpdate = async (row ?: ProductAttrVO) => {
    reset();
    const _id = row?.id || ids.value[0]
    const res = await getProductAttr(_id);
    Object.assign(form.value, res.data);
    dialog.visible = true;
    dialog.title = "修改商品属性";
  }

  /** 提交按钮 */
  const submitForm = () => {
    productAttrFormRef.value?.validate(async (valid : boolean) => {
      if (valid) {
        buttonLoading.value = true;
        if (form.value.id) {
          await updateProductAttr(form.value).finally(() => buttonLoading.value = false);
        } else {
          await addProductAttr(form.value).finally(() => buttonLoading.value = false);
        }
        proxy?.$modal.msgSuccess("操作成功");
        dialog.visible = false;
        await getList();
      }
    });
  }

  /** 删除按钮操作 */
  const handleDelete = async (row ?: ProductAttrVO) => {
    const _ids = row?.id || ids.value;
    await proxy?.$modal.confirm('是否确认删除商品属性编号为"' + _ids + '"的数据项？').finally(() => loading.value = false);
    await delProductAttr(_ids);
    proxy?.$modal.msgSuccess("删除成功");
    await getList();
  }

  /** 导出按钮操作 */
  const handleExport = () => {
    proxy?.download('admin/productAttr/export', {
      ...queryParams.value
    }, `productAttr_${new Date().getTime()}.xlsx`)
  }

  onMounted(() => {
    getList();
  });
</script>
