<template>
  <a-layout>
    <a-layout-content
            :style="{ background: '#fff', padding: '24px', margin: 0, minHeight: '280px' }"
    >
      <p>
      <a-form
              layout="inline"
              :model="param"
      >

        <a-form-item>
          <a-button type="primary" @click="myadd" size="large" style="margin-left: 20px;">新增</a-button>
        </a-form-item>

      </a-form>

      </p>

      <a-table
              :columns="columns"
              :row-key="record => record.id"
              :data-source="level1"
              :loading="loading"
              :pagination="false"

      >
        <template #pic="{ text: pic }">
          <img v-if="pic" :src="pic" alt="avatar" />
        </template>
        <template v-slot:action="{ text, record }">
          <a-space size="small">
            <a-button type="primary" @click="myedit(record)">编辑</a-button>

            <a-popconfirm
                    title="是否删除"
                    ok-text="是"
                    cancel-text="否"
                    @confirm="mydelete(record)"

            >
              <a-button type="danger" >
                删除
              </a-button>
            </a-popconfirm>


          </a-space>
        </template>
      </a-table>
    </a-layout-content>
  </a-layout>

  <a-modal
          v-model:visible="modalVisible"
          title="分类表单"
          :confirm-loading="modalLoading"
          @ok="handleModalOk"
  >
    <a-form :model="category" :label-col="{ span: 6 }" :wrapper-col="{ span: 18 }">
      <a-form-item label="名称">
        <a-input v-model:value="category.name" />
      </a-form-item>

      <a-form-item label="父分类">
        <a-select
            ref="select"
            v-model:value="category.parent"
        >
          <a-select-option value="0">无</a-select-option>
          <a-select-option v-for="c in level1" :key="c.id" :value="c.id" :disabled="category.id === c.id">{{c.name}}</a-select-option>
        </a-select>

      </a-form-item>

      <a-form-item label="排序">
        <a-input v-model:value="category.sort" />
      </a-form-item>
    </a-form>

  </a-modal>

</template>

<script lang="ts">
  import { defineComponent, onMounted, ref } from 'vue';
  import axios from 'axios';
  import { message } from 'ant-design-vue';
  import {Tool} from "@/util/tool";

  export default defineComponent({
    name: 'AdminCategory',
    setup() {
      const categorys = ref();
      const loading = ref(false);

      const columns = [

        {
          title: '名称',
          dataIndex: 'name'
        },
        {
          title: '排序',
          key: 'sort',
          dataIndex: 'sort'
        },
        {
          title: '操作',
          key: 'action',
          slots: { customRender: 'action' }
        }
      ];
      /**
       * 一级分类树， children属性是二级分类
       * [{
       *   id:"",
       *   name:"",
       *   children:[{
       *     id:"",
       *   name:""
       *   }]
       * }]
       */
      const level1 = ref();


      /**
       * 数据查询
       **/
      const handleQuery = () => {
        loading.value = true;
        axios.get("http://localhost:8888/category/search", {
          params: {

          }
        }).then((response) => {
          loading.value = false;
          const data = response.data;
          if (data.success){
            categorys.value = data.list;
            console.log("原始数组：" ,categorys.value);
            level1.value = [];
            level1.value = Tool.array2Tree(categorys.value,0);
            console.log("树形结构",level1.value);

          }else {
            message.error(data.message);
          }

        });
      };

      /**
       * 表格点击页码时触发
       */


      onMounted(() => {
        handleQuery();
      });

      // 编辑表单

      const category=ref({});
      const modalVisible = ref<boolean>(false);
      const modalLoading = ref<boolean>(false);
      const myedit = (record: any) => {
        modalVisible.value = true;
        category.value=Tool.copy(record);
      };

      const handleModalOk = () => {

        modalLoading.value = true;


        axios.post("http://localhost:8888/category/edit", category.value).then((response) => {
          const data = response.data;
          if (data.success){
            //成功后：
            modalVisible.value = false;
            modalLoading.value = false;

            //重新加载列表
            handleQuery();
          }else {
            modalLoading.value = false;
            message.error(data.message);
          }




        });

      };

      // 新增
      const myadd = () => {
        modalVisible.value = true;
        category.value={};
      };

      //删除
      const mydelete = (record: any) => {
        category.value=record;
        axios.post("http://localhost:8888/category/delete", category.value).then((response) => {

          //成功后重新加载列表
          handleQuery();


        });
      };

      //






      return {
        // categorys,

        columns,
        loading,

        modalVisible,
        modalLoading,
        myedit,
        myadd,
        mydelete,
        handleModalOk,
        category,
        level1,



      }
    }
  });
</script>

<style scoped>
  img {
    width: 50px;
    height: 50px;
  }
</style>