// @ts-check
import { defineConfig } from 'astro/config';
import starlight from '@astrojs/starlight';

// https://astro.build/config
export default defineConfig({
	integrations: [
		starlight({
			title: 'Funmands',
			social: [
				{ icon: 'github', label: 'GitHub', href: 'https://github.com/SlimifiedxD/funmands' },
				{ icon: 'discord', label: 'Discord', href: 'https://discord.gg/gWUuu6nw' }
			],
			sidebar: [
				{
					label: 'Walkthrough',
					items: [
						{ slug: 'index' },
						{ slug: 'getting-started' },
						{ slug: 'formats' },
						{
							label: 'Contexts',
							items: [
								{ slug: 'context' },
								/*{
									label: 'Pre Contexts'
								}*/
								{ slug: 'context/senders-and-executors' }
							],
						}
					],
				},
			],
		}),
	],
});
