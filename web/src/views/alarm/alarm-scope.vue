<template>

  <a-layout>

    <a-row type="flex" style="gap: 16px; margin-top: 40px; margin-left: 10px;">
    <a-input
        v-model:value="scopename"
        placeholder="门店名称"
        style="width: 10%"
    />
    <a-input
        v-model:value="brand"
        placeholder="品牌"
        style="width: 10%"
    />

      <a-select v-model:value="is_monitor" style="width: 10%" placeholder="是否监控">
        <a-select-option value="true">可监控</a-select-option>
        <a-select-option value="false">无需监控</a-select-option>
        <a-select-option value="">全部</a-select-option>
      </a-select>

      <a-select v-model:value="is_alarm" style="width: 10%" placeholder="是否报警">
        <a-select-option value="true">可报警</a-select-option>
        <a-select-option value="false">无需报警</a-select-option>
        <a-select-option  value="">全部</a-select-option>
      </a-select>

    <a-button type="primary" @click="searchscope(scopename,brand,is_monitor,is_alarm)">查找</a-button>
    </a-row>

  </a-layout>
  <a-table
      :columns="columns"
      :row-key="record => record.scope_id"
      :data-source="scopelist"
      :pagination="pagination"
      :loading="loading"
      @change="handleTableChange"

  >


  </a-table>


</template>

<script lang="ts">
  import { defineComponent, onMounted, ref } from 'vue';
  import axios from 'axios';
  import { message } from 'ant-design-vue';


  export default defineComponent({
    name: 'AlarmScope',
    setup() {
      const pagination = ref({
        current: 1,
        pageSize: 10,
        total: 0
      });
      const loading = ref(false);

      const scopename = ref<string>('');
      const brand = ref<string>('');
      const is_monitor = ref<boolean>();
      const is_alarm = ref<boolean>();

      const searchscope = (scopename: string, brand: string, is_monitor: boolean | undefined, is_alarm: boolean | undefined) => {
        loading.value = true;
        axios.post("http://localhost:8888/scope/searchpara", {

            "scope_name":scopename,
            "brand":brand,
            "is_monitor":is_monitor,
            "is_alarm":is_alarm

        }).then((response) => {
          loading.value = false;
          const data = response.data;
          if (data.success){
            scopelist.value = data.list;

            // 重置分页按钮
            pagination.value.current = 1;
            pagination.value.total= data.total;
          }else {
            message.error(data.message);
          }

        });
      };





      const columns = [
        {
          title: '门店id',
          dataIndex: 'scope_id',
          key: 'scope_id'

        },
        {
          title: '门店名称',
          dataIndex: 'scope_name',
          key: 'scope_name'
        },
        {
          title: '品牌',
          dataIndex: 'brand',
          key: 'brand'
        },
        {
          title: '是否监控',
          dataIndex: 'is_monitor',
          key: 'is_monitor',
          customRender:(text: any)=>{
            console.log("xxx",text)
            return text.record.is_monitor ? '是' : '否'

          }
        },
        {
          title: '是否报警',
          dataIndex: 'is_alarm',
          key: 'is_alarm',
          customRender:(text: any)=>{
            return text.record.is_alarm ? '是' : '否'
          }
        }
      ];

      const scopelist = ref();
      /**
       * 门店列表查询
       * @param params
       */
      const handleQuery = (params: any) => {
        loading.value = true;
        axios.get("http://localhost:8888/scope/search", {
          params: {
            page: params.page,
            size: params.size
          }
        }).then((response) => {
          loading.value = false;
          const data = response.data;
          if (data.success){
            scopelist.value = data.list;
            console.log("结果",scopelist.value);

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



      return {
        scopename,
        brand,
        is_monitor,
        is_alarm,
        searchscope,
        columns,
        pagination,
        loading,
        handleQuery,
        scopelist,
        handleTableChange,
      };
    }
  })

</script>

<style scoped>
  img {
    width: 50px;
    height: 50px;
  }
</style>