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
          <a-input v-model:value="param.name" placeholder="名称">
          </a-input>
        </a-form-item>


        <a-form-item>
          <a-button type="primary" @click="searchpara({page:1,size:pagination.pageSize})" size="large">查询</a-button>

        </a-form-item>
        <a-form-item>
          <a-button type="primary" @click="myadd" size="large" style="margin-left: 20px;">新增</a-button>
        </a-form-item>
        <a-form-item>
          <a-button type="primary" @click="mymock" size="large" style="margin-left: 20px;">mock测试</a-button>
        </a-form-item>
      </a-form>


      </p>

      <a-table
              :columns="columns"
              :row-key="record => record.id"
              :data-source="ebooks"
              :pagination="pagination"
              :loading="loading"
              @change="handleTableChange"
      >
        <template #pic="{ text: pic }">
          <img v-if="pic" :src="pic" alt="avatar" />
        </template>
        <template v-slot:category="{text, record}">

          {{ getCategoryName(record.category)}}
        </template>
        <template v-slot:action="{ text, record }">
          <a-space size="small">
            <a-button type="primary" @click="myedit(record)">编辑</a-button>
            <a-button type="primary" @click="myeditContent(record)">编辑内容</a-button>

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

          <a-modal
              v-model:visible="isdoc"
              title="电子书编辑内容"
              :confirm-loading="modalLoading"
              @ok="handleDocModalOk(record)"
              :width="1150">

            <div id="content" :style="{ width: '1100px' }"></div>

          </a-modal>


        </template>
      </a-table>
    </a-layout-content>
  </a-layout>

  <a-modal
          v-model:visible="modalVisible"
          title="电子书表单"
          :confirm-loading="modalLoading"
          @ok="handleModalOk"
  >
    <a-form :model="ebook" :label-col="{ span: 6 }" :wrapper-col="{ span: 18 }">
      <a-form-item label="封面">
        <a-input v-model:value="ebook.pic" />
      </a-form-item>
      <a-form-item label="名称">
        <a-input v-model:value="ebook.name" />
      </a-form-item>
      <a-form-item label="分类">
        <a-input v-model:value="ebook.category" />
        <a-cascader v-model:value="categoryIds"
                    :field-names="{ label:'name' , value:'id',children:'children'}"
                    :options="level1"
        />

      </a-form-item>
    </a-form>

  </a-modal>



</template>

<script lang="ts">
  import { defineComponent, onMounted, ref } from 'vue';
  import axios from 'axios';
  import { message } from 'ant-design-vue';
  import {Tool} from "@/util/tool";
  import E from 'wangeditor'

  export default defineComponent({
    name: 'AdminEbook',
    setup() {
      const ebooks = ref();
      const pagination = ref({
        current: 1,
        pageSize: 10,
        total: 0
      });
      const loading = ref(false);

      const columns = [
        {
          title: '封面',
          dataIndex: 'pic',
          slots: { customRender: 'pic' }
        },
        {
          title: '名称',
          dataIndex: 'name'
        },
        {
          title: '分类',
          key: 'category',
          slots: {customRender: 'category'}
        },
        {
          title: '操作',
          key: 'action',
          slots: { customRender: 'action' }
        }
      ];

      /**
       * 数据查询
       **/
      const handleQuery = (params: any) => {
        loading.value = true;
        axios.get("http://localhost:8888/book/search", {
          params: {
            page: params.page,
            size: params.size
          }
        }).then((response) => {
          loading.value = false;
          const data = response.data;
          if (data.success){
            ebooks.value = data.list;

            // 重置分页按钮
            pagination.value.current = params.page;
            pagination.value.total= data.total;
          }else {
            message.error(data.message);
          }

        });
      };

      /**
       * 表格点击页码时触发
       */
      const handleTableChange = (pagination: any) => {
        console.log("看看自带的分页参数都有啥：" + pagination);
        handleQuery({
          page: pagination.current,
          size: pagination.pageSize
        });
      };

      onMounted(() => {
        handleQueryCategory();
        handleQuery({
          page:1,
          size:pagination.value.pageSize
        });



      });

      // 编辑表单

      const categoryIds = ref();
      const ebook=ref();
      const modalVisible = ref<boolean>(false);
      const modalLoading = ref<boolean>(false);
      const isdoc = ref(false);
      const  editor = new E('#content');


      //编辑电子书内容
      const myeditContent = (record: any) => {
        // 表单可见
        isdoc.value = true;
        setTimeout(function () {

          // if(editor){
          //   editor.destroy();
          //
          // }
          //
          editor.create();

        })


        // 调接口取 电子书内容
        axios.post("http://localhost:8888/doc/content", {"ebookid":record.id}).then((response) => {
          const mycontent = response.data;
          console.log("response.data   ",response.data)
          if(mycontent == null || mycontent==""){
            editor.txt.html(" ");
          }else {
            editor.txt.html(mycontent);
          }


        });

      };
      const handleDocModalOk = (record: any) => {
        //编辑保存内容
        let content =editor.txt.html();
        axios.post("http://localhost:8888/doc/edit", {"ebookid":record.id,"content":content}).then((response) => {
          const data = response.data;
          if (data.success){
            //成功后：
            isdoc.value = false;
            handleQuery({
              page:1,
              size:pagination.value.pageSize
            });
            // editor.destroy();

          }else {
            message.error(data.message);
          }


        });
      };


      const myedit = (record: any) => {
        modalVisible.value = true;
        ebook.value=Tool.copy(record);
        categoryIds.value = [ebook.value.category];
      };

      const handleModalOk = () => {

        modalLoading.value = true;
        ebook.value.category = categoryIds.value[1];


        axios.post("http://localhost:8888/book/edit", ebook.value).then((response) => {
          const data = response.data;
          if (data.success){
            //成功后：
            modalVisible.value = false;
            modalLoading.value = false;

            //重新加载列表
            handleQuery({
              page:1,
              size:pagination.value.pageSize
            });
          }else {
            modalLoading.value = false;
            message.error(data.message);
          }

        });

      };

      // 新增
      const myadd = () => {
        modalVisible.value = true;
        ebook.value={};
      };

      //删除
      const mydelete = (record: any) => {
        ebook.value=record;
        axios.post("http://localhost:8888/book/delete", ebook.value).then((response) => {

          //成功后重新加载列表
          handleQuery({
            page:1,
            size:pagination.value.pageSize
          });


        });
      };

      //
      const mymock = () => {
        axios.get("http://localhost:8899/book/search", {
          params: {
            page: 1,
            size: 20
          }
        }).then((response) => {

          const data = response.data;
          ebooks.value = data.list;

        });
      };

      //搜索
      const param = ref();
      param.value={}

      const searchpara = (params: any) => {
        loading.value = true;

        axios.get("http://localhost:8888/book/searchpara", {
          params: {
            page: params.page,
            size: params.size,
            name: param.value.name
          }
        }).then((response) => {
          loading.value = false;
          const data = response.data;
          if (data.success){
            ebooks.value = data.list;

            // 重置分页按钮
            pagination.value.current = params.page;
            pagination.value.total= data.total;
          }else {
            message.error(data.message);
          }

        });
      };

      const level1 = ref();
      let categorys : any;

      /**
       * 查询所有分类
       */
      const handleQueryCategory = () => {
        loading.value = true;
        axios.get("http://localhost:8888/category/search", {
          params: {

          }
        }).then((response) => {
          loading.value = false;
          const data = response.data;
          if (data.success){
            categorys = data.list;
            console.log("原始数组：" ,categorys);
            level1.value = [];
            level1.value = Tool.array2Tree(categorys,0);
            console.log("树形结构",level1.value);

          }else {
            message.error(data.message);
          }

        });
      };

      let result="";

      const  getCategoryName=(cid: any) =>{

        categorys.forEach((item: any)=> {
          if (item.id == cid){
            result = item.name;
          }
        });
        return result;
      };



      return {
        ebooks,
        pagination,
        columns,
        loading,
        handleTableChange,

        modalVisible,
        modalLoading,
        myedit,
        myadd,
        mymock,
        mydelete,
        handleModalOk,
        ebook,
        myeditContent,
        handleDocModalOk,

        param,
        searchpara,

        categoryIds,
        level1,
        getCategoryName,
        isdoc

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