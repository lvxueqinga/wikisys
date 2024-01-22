<template>
  <a-layout>
    <a-layout-content
            :style="{ background: '#fff', padding: '24px', margin: 0, minHeight: '280px' }"
    >
      <p>
        <a-button type="primary" @click="myadd" size="large">新增</a-button>
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
      </a-form-item>
    </a-form>

  </a-modal>

</template>

<script lang="ts">
  import { defineComponent, onMounted, ref } from 'vue';
  import axios from 'axios';
  import { message } from 'ant-design-vue';

  export default defineComponent({
    name: 'AdminEbook',
    setup() {
      const ebooks = ref();
      const pagination = ref({
        current: 1,
        pageSize: 2,
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
          dataIndex: 'category'
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
          ebooks.value = data.list;

          // 重置分页按钮
          pagination.value.current = params.page;
          pagination.value.total= data.total;
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
        handleQuery({
          page:1,
          size:pagination.value.pageSize
        });
      });

      // 编辑表单

      const ebook=ref({});
      const modalVisible = ref<boolean>(false);
      const modalLoading = ref<boolean>(false);
      const myedit = (record: any) => {
        modalVisible.value = true;
        ebook.value=record;
      };

      const handleModalOk = () => {

        modalLoading.value = true;


        axios.post("http://localhost:8888/book/edit", ebook.value).then((response) => {
          const data = response.data;
          //成功后：
            modalVisible.value = false;
            modalLoading.value = false;

            //重新加载列表
            handleQuery({
              page:1,
              size:pagination.value.pageSize
            });


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

          console.log("ebook.value：" + ebook.value);
          //成功后重新加载列表
          handleQuery({
            page:1,
            size:pagination.value.pageSize
          });


        });
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
        mydelete,
        handleModalOk,
        ebook,



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