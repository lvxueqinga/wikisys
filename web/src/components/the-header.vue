<template>
    <a-layout-header class="header">
        <div class="logo" />
        <a-menu
                v-model:selectedKeys="selectedKeys1"
                theme="dark"
                mode="horizontal"
                :style="{ lineHeight: '64px' }"
        >
            <a-menu-item key="1">首页</a-menu-item>
            <a-menu-item key="2">电子书管理</a-menu-item>
            <a-menu-item key="3">关于我们</a-menu-item>
        </a-menu>
    </a-layout-header>
</template>

<script lang="ts">
    import { defineComponent } from 'vue';

    export default defineComponent({
        name: 'the-header',

    });
</script>