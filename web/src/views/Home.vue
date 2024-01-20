<template>
  <div class="home">

        {{ebooks}}

  </div>
</template>

<script lang="ts">
import { defineComponent , onMounted , ref} from 'vue';
import HelloWorld from '@/components/HelloWorld.vue'; // @ is an alias to /src
import axios from 'axios';


export default defineComponent({
  name: 'Home',
  components: {
    HelloWorld,

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


    return {
      ebooks,
    };
  },
});

</script>
