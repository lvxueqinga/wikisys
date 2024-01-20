<template>
    <a-list :grid="{ gutter: 16, column: 3 }" item-layout="vertical" size="large" :pagination="pagination" :data-source="ebooks">

        <template #renderItem="{ item }">
            <a-list-item key="item.name">
                <template #actions>
          <span v-for="{ type, text } in actions" :key="type">
            <component :is="type" style="margin-right: 8px" />
            {{ text }}
          </span>
                </template>

                <a-list-item-meta :description="item.id">
                    <template #title>
                        <a :href="item.href">{{ item.name }}</a>
                    </template>
                    <template #avatar><a-avatar :src="item.pic" /></template>
                </a-list-item-meta>

            </a-list-item>
        </template>
    </a-list>
</template>

<script lang="ts">
import { defineComponent , onMounted , ref} from 'vue';
import HelloWorld from '@/components/HelloWorld.vue'; // @ is an alias to /src
import axios from 'axios';
import { StarOutlined, LikeOutlined, MessageOutlined } from '@ant-design/icons-vue';



export default defineComponent({
  name: 'Home',
  components: {
    HelloWorld,
      StarOutlined,
      LikeOutlined,
      MessageOutlined,

  },
  setup() {
    console.log("mysetup");
    const ebooks = ref();

    onMounted(() => {
      console.log("onMounted");
      axios.get("http://localhost:8888/book/search").then((response) => {
        console.log(response);
        const data = response.data;
        ebooks.value = data;
      });
    });

      const pagination = {
          onChange: (page: number) => {
              console.log(page);
          },
          pageSize: 3,
      };
      const actions: Record<string, string>[] = [
          { type: 'StarOutlined', text: '156' },
          { type: 'LikeOutlined', text: '156' },
          { type: 'MessageOutlined', text: '2' },
      ];



    return {
      ebooks,
        pagination,
        actions,
    };
  },
});





</script>


<style scoped>
    .ant-avatar {
        width: 50px;
        height: 50px;
        line-height: 50px;
        border-radius: 8%;
        margin: 5px 0;
    }
</style>